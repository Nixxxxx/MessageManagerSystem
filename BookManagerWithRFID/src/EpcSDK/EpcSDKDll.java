package EpcSDK;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.HeapMemoryBlock;

/**
 * @author zhengwuzhi 调用DLL使用jnative.jar实现
 * 
 */
public class EpcSDKDll {
	public static String EpcDll = "EPCSDK.dll";
	public int com;

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 把16进制字符串转换成字节数组（自动把字符串转换为大写）
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
		hex = hex.toUpperCase();
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 验证字符串是否16进制
	 * 
	 * @param hex
	 *            16进制字符串
	 * @return
	 */
	public static boolean matcherStringIsHex(String hex) {
		Pattern p = Pattern.compile("[0-9a-fA-F]+");
		Matcher matcher = p.matcher(hex);
		return matcher.matches();
	}

	/**
	 * 函数原型： HANDLE OpenComm(int readAddr); 功能说明： 打开串口。
	 * 
	 * @param readAddr
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int openComm(int readAddr) throws NativeException,IllegalAccessException {
		JNative OpenComm = new JNative(EpcDll, "OpenComm");
		OpenComm.setRetVal(Type.INT);
		OpenComm.setParameter(0, readAddr);
		OpenComm.invoke();
		com = Integer.parseInt(OpenComm.getRetVal());
		return com;
	}

	/**
	 * 函数原型： void CloseComm(HANDLE hCom); 功能说明： 关闭串口。
	 * 
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public void closeComm() throws NativeException, IllegalAccessException {
		JNative StopReading = new JNative(EpcDll, "StopReading");
		StopReading.setParameter(0, this.com);
		StopReading.invoke();
	}

	/**
	 * 函数原型： BOOL StopReading(HANDLE hCom ,BYTE ReaderAddr); 功能说明： 使读卡器停止读取标签。
	 * 返回值： 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param Port
	 *            串口句柄
	 * @param readAddr
	 *            读头地址，一台主机接多台读头时使用，接单台读头时置为0；
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int stopReading(int readAddr) throws NativeException,
			IllegalAccessException {
		JNative StopReading = new JNative(EpcDll, "StopReading");
		StopReading.setRetVal(Type.INT);
		StopReading.setParameter(0, this.com);
		StopReading.setParameter(1, readAddr);
		StopReading.invoke();
		int result = Integer.parseInt(StopReading.getRetVal());
		return result;
	}

	/**
	 * 函数原型： BOOL ResumeReading(HANDLE hCom,BYTE ReaderAddr); 功能说明：
	 * 使读卡器恢复读取标签（复位读头）。 返回值： 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param readAddr
	 *            读头地址，一台主机接多台读头时使用，接单台读头时置为0；
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int resumeReading(int readAddr) throws NativeException,
			IllegalAccessException {
		JNative ResumeReading = new JNative(EpcDll, "ResumeReading");
		ResumeReading.setRetVal(Type.INT);
		ResumeReading.setParameter(0, this.com);
		ResumeReading.setParameter(1, readAddr);
		ResumeReading.invoke();
		int result = Integer.parseInt(ResumeReading.getRetVal());
		return result;
	}

	/**
	 * 函数原型： BOOL IdentifySingleTag(HANDLE hCom, BYTE* tagID, BYTE*
	 * antennaNo，BYTE ReaderAddr); 功能说明： 识别单个标签。 返回值： 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param readAddr
	 * @return 返回HashMasp对象，包含两个参数　 tagID： 接收标签ID的数组地址（输出参数），长度为12 antennaNo：
	 *         接收天线号的变量地址（输出参数）。不需要时置为NULL。
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public HashMap<String, String> identifySingleTag(int readAddr)
			throws NativeException, IllegalAccessException {
		HashMap<String, String> SingleTagMap = new HashMap<String, String>();
		Pointer tagID = new Pointer(new HeapMemoryBlock(12));
		Pointer antennaNo = new Pointer(new HeapMemoryBlock(1));
		JNative IdentifySingleTag = new JNative(EpcDll, "IdentifySingleTag");
		IdentifySingleTag.setRetVal(Type.INT);
		IdentifySingleTag.setParameter(0, this.com);
		IdentifySingleTag.setParameter(1, tagID);
		IdentifySingleTag.setParameter(2, antennaNo);
		IdentifySingleTag.setParameter(3, readAddr);
		IdentifySingleTag.invoke();
		int result = Integer.parseInt(IdentifySingleTag.getRetVal());
		if (result == 1) {
			SingleTagMap.put("tagID", bytesToHexString(tagID.getMemory()));
			SingleTagMap.put("antennaNo",
					bytesToHexString(antennaNo.getMemory()));
		} else {
			SingleTagMap = null;
		}
		return SingleTagMap;
	}

	/**
	 * 测试成功，模式必须设置为＂定时模式＂或＂触发模式＂方可读取成功． 函数原型： BOOL
	 * IdentifyUploadedSingleTag(HANDLE hCom, BYTE* tagID, BYTE* devNo=NULL,
	 * BYTE* antennaNo); 功能说明： 识别读卡器上传的单个标签。 返回值： 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @return 返回值 tagID： 接收标签ID的数组地址（输出参数），长度为12 devNo：
	 *         接收设备号的变量地址（输出参数）。不需要时置为NULL。 antennaNo：
	 *         接收天线号的变量地址（输出参数）。不需要时置为NULL。
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public HashMap<String, String> identifyUploadedSingleTag()
			throws NativeException, IllegalAccessException {
		HashMap<String, String> UploadedSingleTag = new HashMap<String, String>();
		Pointer tagID = new Pointer(new HeapMemoryBlock(12));
		Pointer antennaNo = new Pointer(new HeapMemoryBlock(1));
		Pointer devNo = new Pointer(new HeapMemoryBlock(1));
		JNative IdentifyUploadedSingleTag = new JNative(EpcDll,
				"IdentifyUploadedSingleTag");
		IdentifyUploadedSingleTag.setRetVal(Type.INT);
		IdentifyUploadedSingleTag.setParameter(0, this.com);
		IdentifyUploadedSingleTag.setParameter(1, tagID);
		IdentifyUploadedSingleTag.setParameter(2, devNo);
		IdentifyUploadedSingleTag.setParameter(3, antennaNo);
		IdentifyUploadedSingleTag.invoke();
		int result = Integer.parseInt(IdentifyUploadedSingleTag.getRetVal());
		if (result == 1) {
			UploadedSingleTag.put("tagID", bytesToHexString(tagID.getMemory()));
			UploadedSingleTag.put("antennaNo",
					bytesToHexString(antennaNo.getMemory()));
			UploadedSingleTag.put("devNo", bytesToHexString(devNo.getMemory()));
		} else {
			UploadedSingleTag = null;
		}
		return UploadedSingleTag;
	}

	/**
	 * 测试成功，模式必须设置为＂定时模式＂或＂触发模式＂方可读取成功． 函数原型： BOOL
	 * IdentifyUploadedMultiTags(HANDLE hCom, BYTE* tagNum, BYTE* tagIDs, BYTE*
	 * devNos=NULL, BYTE* antennaNos); 功能说明： 识别读卡器上传的多个标签。 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @return 返回值： tagNum： 接收标签数的变量地址（输出参数）。一次可能读取的最大标签数是200。 已转换为10进制数 tagIDs：
	 *         接收标签ID的数组地址（输出参数），长度为12 * tagNum 返回字符串数组 devNos：
	 *         接收设备号的数组地址（输出参数）。长度为1 * tagNum，不需要时置为NULL。 antennaNos：
	 *         接收天线号的数组地址（输出参数）。长度为1 * tagNum，不需要时置为NULL。 假设读到10个标签时：
	 *         *tagNum的值为10；
	 *         tagIDs[0~11]、tagIDs[12~23]……、tagIDs[108~119]分别为第1个、第2个
	 *         ……第10个标签的ID； devNos[0]、devNos[1]……devNos[9]分别为第1个、第2个……第10标签的设备号；
	 *         antennaNos[0]、antennaNos[1]……antennaNos[9]为第1个、第2个……第10个标签的天线号；
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public HashMap<String, Object> identifyUploadedMultiTags()
			throws NativeException, IllegalAccessException {
		HashMap<String, Object> tags = new HashMap<String, Object>();
		Pointer tagNumPointer = new Pointer(new HeapMemoryBlock(1));
		Pointer tagIDsPointer = new Pointer(new HeapMemoryBlock(12 * 30));
		Pointer devNosPointer = new Pointer(new HeapMemoryBlock(30));
		Pointer antennaNosPointer = new Pointer(new HeapMemoryBlock(30));
		JNative method = new JNative(EpcDll, "IdentifyUploadedMultiTags");
		method.setRetVal(Type.INT);
		method.setParameter(0, this.com);
		method.setParameter(1, tagNumPointer);
		method.setParameter(2, tagIDsPointer);
		method.setParameter(3, devNosPointer);
		method.setParameter(4, antennaNosPointer);
		method.invoke();
		int result = Integer.parseInt(method.getRetVal());
		if (result == 1) {
			int tagNum = Integer.parseInt(
					bytesToHexString(tagNumPointer.getMemory()), 16);
			Set<String> tagIds = new HashSet<String>(tagNum);
			String tagIDStr = bytesToHexString(tagIDsPointer.getMemory());
			for (int i = 0; i < tagNum; i++) {
				tagIds.add(tagIDStr.substring(i * 24, (i + 1) * 24));
			}
			tags.put("tagNum", tagIds.size());
			tags.put("tagIDs", tagIds);
			tags.put("devNos", bytesToHexString(devNosPointer.getMemory()));
			tags.put("antennaNos",
					bytesToHexString(antennaNosPointer.getMemory()));
		} else {
			tags = null;
		}
		return tags;
	}

	/**
	 * 函数原型： BOOL ReadTag(HANDLE hCom, BYTE memBank, BYTE address, BYTE length,
	 * BYTE* data, BYTE ReaderAddr); 功能说明： 读标签内容。 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param memBank
	 *            要读的区域。各值的意义如下： 0——保留区 1——EPC区 2——TID区 3——用户区
	 * @param address
	 *            要读区域中的地址，取值为范围0-7。
	 * @param length
	 *            要读取的长度，取值范围是1到8（1Word = 2Byte =
	 *            4位）（说明：memBank=EPC区，address+length的值不超过8
	 *            ．memBank=保留区，address+length的值不超过4．）。
	 * @param readAddr
	 *            读头地址，一台主机接多台读头时使用，接单台读头时置为0；
	 * @return 成功时：返回16进制数的字符串 失败时：返回-1
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public String readTag(int memBank, int address, int length, int readAddr)
			throws NativeException, IllegalAccessException {
		if (memBank == 1 && (address + length > 8)) {
			throw new NativeException(
					"读取EPC区内容时，address(读区域中的地址)+length(要读取的长度)的值不超过8．请检查输入参数值！");
		}
		if (memBank == 0 && (address + length > 4)) {
			throw new NativeException(
					"读取保留区内容时，address(读区域中的地址)+length(要读取的长度)的值不超过4．请检查输入参数值！");
		}
		Pointer PointOpen = new Pointer(new HeapMemoryBlock(length * 2));
		JNative ReadTag = new JNative(EpcDll, "ReadTag");
		ReadTag.setRetVal(Type.INT);
		ReadTag.setParameter(0, this.com);
		ReadTag.setParameter(1, memBank);
		ReadTag.setParameter(2, address);
		ReadTag.setParameter(3, length);
		ReadTag.setParameter(4, PointOpen);
		ReadTag.setParameter(5, readAddr);
		ReadTag.invoke();
		int result = Integer.parseInt(ReadTag.getRetVal());
		if (result == 1) {
			return bytesToHexString(PointOpen.getMemory());
		} else {
			return "-1";
		}
	}

	/**
	 * 函数原型： BOOL WriteTagSingleWord(HANDLE hCom, BYTE memBank, BYTE address,
	 * BYTE data1, BYTE data2, BYTE ReaderAddr); 功能说明：
	 * 向标签写入1个字（2字节）的内容。（注：EPC区的地址0、1不可写入） 返回值： 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param memBank
	 *            要写的区域。各值的意义如下： 0——保留区 1——EPC区 2——TID区 3——用户区
	 * @param address
	 *            要写区域中的地址，取值为范围0-7（memBank为EPC区时， 0、 1不可取）。
	 * @param data
	 *            要写入内容的4位16进制字符串（如果只传入1个字节数，第2个字节自动补00，因为直接控制传入的字符长度及类型）
	 * @param readAddr
	 *            读头地址，一台主机接多台读头时使用，接单台读头时置为0；
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int writeTagSingleWord(int memBank, int address, String hex,
			int readAddr) throws NativeException, IllegalAccessException {
		int result = 0;
		if (memBank >= 0 && memBank <= 3) {
			if (memBank == 1 && (address == 0 || address == 1)) {
				throw new NativeException(
						"当memBank为EPC区时， 要写区域中的地址address不能等于0或1，只能取值2-7！");
			} else {
				// 16进制字符串长度必须等于4
				if (hex.length() == 4 && matcherStringIsHex(hex)) {
					byte[] data = hexStringToByte(hex);
					JNative WriteTagSingleWord = new JNative(EpcDll,
							"WriteTagSingleWord");
					WriteTagSingleWord.setRetVal(Type.INT);
					WriteTagSingleWord.setParameter(0, this.com);
					WriteTagSingleWord.setParameter(1, memBank);
					WriteTagSingleWord.setParameter(2, address);
					WriteTagSingleWord.setParameter(3, data[0]);
					WriteTagSingleWord.setParameter(4, data[1]);
					WriteTagSingleWord.setParameter(5, readAddr);
					WriteTagSingleWord.invoke();
					result = Integer.parseInt(WriteTagSingleWord.getRetVal());
				} else {
					throw new NativeException(
							"写入字符长度必须4位并且为16进制字符，请检查输入hex参数值．");
				}
			}
		} else {
			throw new NativeException(
					"要写的区域值错误，写入区域值范围0-3．0——保留区，1——EPC区，2——TID区，3——用户区");
		}
		System.out.println("writeTagSingleWord:" + result);
		return result;
	}

	/**
	 * 函数原型： BOOL FastWriteTagID(HANDLE hCom, int bytesNum, const BYTE* bytes,
	 * BYTE ReaderAddr); 功能说明：
	 * 快写标签ID（快写标签的16进制字符是从后开始写进标签卡中,奇数长度的字符串最后一位不写入标签卡并00代替写入标签卡中） 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param Port
	 *            串口句柄
	 * @param byteCount
	 *            要写入内容的字节数，必须为2，4，6，8，10，或12。
	 * @param data
	 *            要写入内容
	 * @param readAddr
	 *            读头地址，一台主机接多台读头时使用，接单台读头时置为0；
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int fastWriteTagID(int byteCount, String data, int readAddr)
			throws NativeException, IllegalAccessException {
		Pointer dataPointer = new Pointer(new HeapMemoryBlock(byteCount));
		dataPointer.setMemory(EpcSDKDll.hexStringToByte(data));
		JNative method = new JNative(EpcDll, "FastWriteTagID");
		method.setRetVal(Type.INT);
		method.setParameter(0, this.com);
		method.setParameter(1, byteCount);
		method.setParameter(2, dataPointer);
		method.setParameter(3, readAddr);
		method.invoke();
		int result = Integer.parseInt(method.getRetVal());
		System.out.println("fastWriteTagID:" + result);
		return result;
	}

	/**
	 * BOOL FastWriteTag(HANDLE hCom, BYTE memBank, BYTE address, BYTE
	 * WordCount,const BYTE* bytes,BYTE ReaderAddr)
	 * 功能说明：快写标签（快写标签的16进制字符是从后开始写进标签卡中,奇数长度的字符串最后一位不写入标签卡并00代替写入标签卡中） 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param memBank
	 *            要写入标签的区域。各值的意义如下：0x00——保留区 0x01——EPC区 0x02——TID区 0x03——用户区
	 * @param address
	 *            要写入区域中的地址，memBank为EPC区时，地址范围为2-7，最大WordCount为6；
	 *            为保留区时，地址范围为0-3，最大WordCount为4； 为数据区时，址址范围为0-31，最大WordCount为8；
	 * @param wordCount
	 *            要写入内容的长度，以字为单位，1个字为2个字节
	 * @param data
	 *            要写入的数据
	 * @param readAddr
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int fastWriteTag(int memBank, int address,
			int wordCount, String data, int readAddr) throws NativeException,
			IllegalAccessException {
		Pointer dataPointer = new Pointer(new HeapMemoryBlock(wordCount * 2));
		dataPointer.setMemory(hexStringToByte(data));
		JNative writeByEpcID = new JNative(EpcDll, "FastWriteTag");
		writeByEpcID.setRetVal(Type.INT);
		writeByEpcID.setParameter(0, this.com);
		writeByEpcID.setParameter(1, memBank);
		writeByEpcID.setParameter(2, address);
		writeByEpcID.setParameter(3, wordCount);
		writeByEpcID.setParameter(4, dataPointer);
		writeByEpcID.setParameter(5, readAddr);
		writeByEpcID.invoke();
		int result = Integer.parseInt(writeByEpcID.getRetVal());
		return result;
	}

	/**
	 * 函数原型： BOOL InitializeTag(HANDLE hCom, BYTE ReaderAddr); 功能说明： 初始化标签。 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param readAddr
	 *            读头地址，一台主机接多台读头时使用，接单台读头时置为0；
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int initializeTag(int readAddr) throws NativeException,
			IllegalAccessException {
		JNative InitializeTag = new JNative(EpcDll, "InitializeTag");
		InitializeTag.setRetVal(Type.INT);
		InitializeTag.setParameter(0, this.com);
		InitializeTag.setParameter(1, readAddr);
		InitializeTag.invoke();
		int result = Integer.parseInt(InitializeTag.getRetVal());
		return result;
	}

	/**
	 * 测试失败，原因不明 函数原型 LockPassWordTag (HANDLE hCom,BYTE passwd1,BYTE
	 * passwd2,BYTE passwd3,BYTE passwd4,BYTE lockType, BYTE ReaderAddr);
	 * 功能说明：通过访问密码锁定标签 lockType值 解锁类型 00 UNLOCK USER 01 UNLOCK TID 02 UNLOCK EPC
	 * 03 UNLOCK ACCESS 04 UNLOCK KILL 05 UNLOCK ALL 其他值 DO NOT UNLOCK
	 * 
	 * @param password
	 * @param lockType
	 * @param readAddr
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int lockPassWordTag(String password, int lockType, int readAddr)
			throws NativeException, IllegalAccessException {
		int result = 0;
		byte[] passwordbyte;
		if (matcherStringIsHex(password) && password.length() == 8) {
			passwordbyte = hexStringToByte(password);
			JNative LockPassWordTag = new JNative(EpcDll, "LockPassWordTag");
			LockPassWordTag.setRetVal(Type.INT);
			LockPassWordTag.setParameter(0, this.com);
			LockPassWordTag.setParameter(1, passwordbyte[0]);
			LockPassWordTag.setParameter(2, passwordbyte[1]);
			LockPassWordTag.setParameter(3, passwordbyte[2]);
			LockPassWordTag.setParameter(4, passwordbyte[3]);
			LockPassWordTag.setParameter(5, lockType);
			LockPassWordTag.setParameter(6, readAddr);
			LockPassWordTag.invoke();
			result = Integer.parseInt(LockPassWordTag.getRetVal());
		} else {
			throw new NativeException("写入字符长度必须8位并且为16进制字符，请检查输入hex参数值．");
		}
		return result;
	}

	/**
	 * 测试失败，原因不明 函数原型UnlockPassWordTag(HANDLE hCom,BYTE passwd1,BYTE
	 * passwd2,BYTE passwd3,BYTE passwd4,BYTE lockType, BYTE ReaderAddr);
	 * 功能说明：通过访问密码解锁标签 lockType值 解锁类型 00 UNLOCK USER 01 UNLOCK TID 02 UNLOCK EPC
	 * 03 UNLOCK ACCESS 04 UNLOCK KILL 05 UNLOCK ALL 其他值 DO NOT UNLOCK
	 * 
	 * @param password
	 * @param lockType
	 * @param readAddr
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int unlockPassWordTag(String password, int lockType, int readAddr)
			throws NativeException, IllegalAccessException {
		int result = 0;
		byte[] passwordbyte;
		if (matcherStringIsHex(password) && password.length() == 8) {
			passwordbyte = hexStringToByte(password);
			JNative UnlockPassWordTag = new JNative(EpcDll,
					"UnlockPassWordTag ");
			UnlockPassWordTag.setRetVal(Type.INT);
			UnlockPassWordTag.setParameter(0, this.com);
			UnlockPassWordTag.setParameter(1, passwordbyte[0]);
			UnlockPassWordTag.setParameter(2, passwordbyte[1]);
			UnlockPassWordTag.setParameter(3, passwordbyte[2]);
			UnlockPassWordTag.setParameter(4, passwordbyte[3]);
			UnlockPassWordTag.setParameter(5, lockType);
			UnlockPassWordTag.setParameter(6, readAddr);
			UnlockPassWordTag.invoke();
			result = Integer.parseInt(UnlockPassWordTag.getRetVal());
		} else {
			throw new NativeException("写入字符长度必须8位并且为16进制字符，请检查输入hex参数值．");
		}
		return result;
	}

	/**
	 * 函数原型： BOOL GetReaderParameters(HANDLE hCom, int addr, int paramNum, BYTE*
	 * params, BYTE ReaderAddr); 功能说明： 获取多个读写器参数。 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param addr
	 *            要查询的读写器参数的起始地址（各参数的对应地址参考附表1-11）
	 * @param paramNum
	 *            要查询的读写器参数的数量
	 * @param readAddr
	 *            读头地址，一台主机接多台读头时使用，接单台读头时置为0；
	 * @return 返回对应地址参数值（注：第一个参数为读取地址，第二个、第三个[以此类推]参数是对应地址的数值．）
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public byte[] getReaderParameters(int addr, int paramNum, int readAddr)
			throws NativeException, IllegalAccessException {
		Pointer PointOpen = new Pointer(new HeapMemoryBlock(paramNum));
		byte[] retsultByte = null;
		JNative GetReaderParameters = new JNative(EpcDll, "GetReaderParameters");
		GetReaderParameters.setRetVal(Type.INT);
		GetReaderParameters.setParameter(0, this.com);
		GetReaderParameters.setParameter(1, addr);
		GetReaderParameters.setParameter(2, paramNum);
		GetReaderParameters.setParameter(3, PointOpen);
		GetReaderParameters.setParameter(4, readAddr);
		GetReaderParameters.invoke();
		int result = Integer.parseInt(GetReaderParameters.getRetVal());
		if (result == 1) {
			retsultByte = PointOpen.getMemory();
		}
		return retsultByte;
	}

	/**
	 * 函数原型： BOOL SetReaderParameters(HANDLE hCom, int addr, int paramNum, const
	 * BYTE* params, BYTE ReaderAddr); 功能说明： 设置多个读写器参数。 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param addr
	 *            要查询的读写器参数的起始地址（各参数的对应地址参考附表1-11）
	 * @param paramNum
	 *            要查询的读写器参数的数量
	 * @param params
	 *            读写器参数的数组地址
	 * @param readAddr
	 *            读头地址，一台主机接多台读头时使用，接单台读头时置为0；
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int setReaderParameters(int addr, int paramNum, byte[] params,
			int readAddr) throws NativeException, IllegalAccessException {
		Pointer ParametersPointOpen = new Pointer(new HeapMemoryBlock(paramNum));
		ParametersPointOpen.setMemory(params);
		JNative SetReaderParameters = new JNative(EpcDll, "SetReaderParameters");
		SetReaderParameters.setRetVal(Type.INT);
		SetReaderParameters.setParameter(0, this.com);
		SetReaderParameters.setParameter(1, addr);
		SetReaderParameters.setParameter(2, paramNum);
		SetReaderParameters.setParameter(3, ParametersPointOpen);
		SetReaderParameters.setParameter(4, readAddr);
		SetReaderParameters.invoke();
		int result = Integer.parseInt(SetReaderParameters.getRetVal());
		return result;
	}

	/**
	 * 测试不成功 函数原型： BOOL ReadTIDByEpcID(HANDLE hCom, const BYTE* bytes,BYTE*
	 * data,BYTE ReaderAddr); 功能说明： 指定标签的EPC区号码（12个字节）读取对应标签的TID区（8个字节） 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param readAddr
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public String readTIDByEpcID(int readAddr) throws NativeException,
			IllegalAccessException {
		Pointer EPCPointOpen = new Pointer(new HeapMemoryBlock(12));
		Pointer TIDPointOpen = new Pointer(new HeapMemoryBlock(8));
		JNative ReadTIDByEpcID = new JNative(EpcDll, "ReadTIDByEpcID");
		ReadTIDByEpcID.setRetVal(Type.INT);
		ReadTIDByEpcID.setParameter(0, this.com);
		ReadTIDByEpcID.setParameter(1, EPCPointOpen);
		ReadTIDByEpcID.setParameter(2, TIDPointOpen);
		ReadTIDByEpcID.setParameter(3, readAddr);
		ReadTIDByEpcID.invoke();
		int result = Integer.parseInt(ReadTIDByEpcID.getRetVal());
		if (result == 1) {
			System.out.println("EPCPointOpen.getMemory()="
					+ bytesToHexString(EPCPointOpen.getMemory()));
			System.out.println("TIDPointOpen.getMemory()="
					+ bytesToHexString(TIDPointOpen.getMemory()));
			return bytesToHexString(EPCPointOpen.getMemory());
		} else {
			return "-1";
		}
	}

	/**
	 * BOOL ReadByEpcID(HANDLE hCom, BYTE memBank, BYTE address, BYTE
	 * WordCount,const BYTE* EpcID, BYTE* data,BYTE ReaderAddr)
	 * 功能说明：指定标签的EPC区号码（12个字节）读取对应标签，通常在主从模式下使用该函数。 返回值：
	 * 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param memBank
	 *            要写入标签的区域。各值的意义如下：0x00——保留区 0x01——EPC区 0x02——TID区 0x03——用户区
	 * @param address
	 *            要写入区域中的地址，memBank为EPC区时，地址范围为2-7，最大WordCount为6；
	 *            为保留区时，地址范围为0-3，最大WordCount为4； 为数据区时，址址范围为0-31，最大WordCount为8；
	 * @param wordCount
	 *            要写入内容的长度，以字为单位，1个字为2个字节
	 * @param epcID
	 *            EPC 号码指针，包含12个字节的EPC数据
	 * @param readAddr
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public String readByEpcID(int memBank, int address, int wordCount,
			String epcID, int readAddr) throws NativeException,
			IllegalAccessException {
		Pointer dataPointer = new Pointer(new HeapMemoryBlock(12));
		Pointer epcIdPointer = new Pointer(new HeapMemoryBlock(12));
		epcIdPointer.setMemory(hexStringToByte(epcID));
		JNative readByEpcID = new JNative(EpcDll, "ReadByEpcID");
		readByEpcID.setRetVal(Type.INT);
		readByEpcID.setParameter(0, this.com);
		readByEpcID.setParameter(1, memBank);
		readByEpcID.setParameter(2, address);
		readByEpcID.setParameter(3, wordCount);
		readByEpcID.setParameter(4, epcIdPointer);
		readByEpcID.setParameter(5, dataPointer);
		readByEpcID.setParameter(6, readAddr);
		readByEpcID.invoke();
		int result = Integer.parseInt(readByEpcID.getRetVal());
		if (result == 1) {
			System.out.println("dataPointer.getMemory()="
					+ bytesToHexString(dataPointer.getMemory()));
			return bytesToHexString(dataPointer.getMemory());
		} else {
			return "-1";
		}
	}

	/**
	 * 函数原型： BOOL WriteByEpcID(HANDLE hCom, BYTE memBank, BYTE address, BYTE
	 * WordCount,const BYTE* EpcID,BYTE* data,BYTE ReaderAddr) 功能说明：
	 * 向指定标签的EPC区号码（12个字节）中的特定地址写入data, 返回值： 成功时返回TRUE（1），失败时返回FALSE（0）
	 * 
	 * @param memBank
	 *            要写入标签的区域。各值的意义如下：0x00——保留区 0x01——EPC区 0x02——TID区 0x03——用户区
	 * @param address
	 *            要写入区域中的地址，memBank为EPC区时，地址范围为2-7，最大WordCount为6；
	 *            为保留区时，地址范围为0-3，最大WordCount为4； 为数据区时，址址范围为0-31，最大WordCount为8；
	 * @param wordCount
	 *            要写入内容的长度，以字为单位，1个字为2个字节
	 * @param epcID
	 *            EPC 号码指针，包含12个字节的EPC数据
	 * @param data
	 *            要写入的数据
	 * @param readAddr
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public int writeByEpcID(int memBank, int address, int wordCount,
			String epcID, String data, int readAddr) throws NativeException,
			IllegalAccessException {

		Pointer epcIDPointer = new Pointer(new HeapMemoryBlock(12));
		epcIDPointer.setMemory(hexStringToByte(epcID));

		Pointer dataPointer = new Pointer(new HeapMemoryBlock(wordCount * 2));
		dataPointer.setMemory(hexStringToByte(data));
		for (byte b : dataPointer.getMemory())
			System.out.print(b + ",");
		JNative writeByEpcID = new JNative(EpcDll, "WriteByEpcID");
		writeByEpcID.setRetVal(Type.INT);
		writeByEpcID.setParameter(0, this.com);
		writeByEpcID.setParameter(1, memBank);
		writeByEpcID.setParameter(2, address);
		writeByEpcID.setParameter(3, wordCount);
		writeByEpcID.setParameter(4, epcIDPointer);
		writeByEpcID.setParameter(5, dataPointer);
		writeByEpcID.setParameter(6, readAddr);
		writeByEpcID.invoke();
		int result = Integer.parseInt(writeByEpcID.getRetVal());
		return result;
	}
}
