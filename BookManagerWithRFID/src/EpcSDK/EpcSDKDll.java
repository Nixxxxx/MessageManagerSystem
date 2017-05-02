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
 * @author zhengwuzhi ����DLLʹ��jnative.jarʵ��
 * 
 */
public class EpcSDKDll {
	public static String EpcDll = "EPCSDK.dll";
	public int com;

	/**
	 * ���ֽ�����ת����16�����ַ���
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
	 * ��16�����ַ���ת�����ֽ����飨�Զ����ַ���ת��Ϊ��д��
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
	 * ��֤�ַ����Ƿ�16����
	 * 
	 * @param hex
	 *            16�����ַ���
	 * @return
	 */
	public static boolean matcherStringIsHex(String hex) {
		Pattern p = Pattern.compile("[0-9a-fA-F]+");
		Matcher matcher = p.matcher(hex);
		return matcher.matches();
	}

	/**
	 * ����ԭ�ͣ� HANDLE OpenComm(int readAddr); ����˵���� �򿪴��ڡ�
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
	 * ����ԭ�ͣ� void CloseComm(HANDLE hCom); ����˵���� �رմ��ڡ�
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
	 * ����ԭ�ͣ� BOOL StopReading(HANDLE hCom ,BYTE ReaderAddr); ����˵���� ʹ������ֹͣ��ȡ��ǩ��
	 * ����ֵ�� �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param Port
	 *            ���ھ��
	 * @param readAddr
	 *            ��ͷ��ַ��һ̨�����Ӷ�̨��ͷʱʹ�ã��ӵ�̨��ͷʱ��Ϊ0��
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
	 * ����ԭ�ͣ� BOOL ResumeReading(HANDLE hCom,BYTE ReaderAddr); ����˵����
	 * ʹ�������ָ���ȡ��ǩ����λ��ͷ���� ����ֵ�� �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param readAddr
	 *            ��ͷ��ַ��һ̨�����Ӷ�̨��ͷʱʹ�ã��ӵ�̨��ͷʱ��Ϊ0��
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
	 * ����ԭ�ͣ� BOOL IdentifySingleTag(HANDLE hCom, BYTE* tagID, BYTE*
	 * antennaNo��BYTE ReaderAddr); ����˵���� ʶ�𵥸���ǩ�� ����ֵ�� �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param readAddr
	 * @return ����HashMasp���󣬰������������� tagID�� ���ձ�ǩID�������ַ�����������������Ϊ12 antennaNo��
	 *         �������ߺŵı�����ַ�����������������Ҫʱ��ΪNULL��
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
	 * ���Գɹ���ģʽ��������Ϊ����ʱģʽ���򣢴���ģʽ�����ɶ�ȡ�ɹ��� ����ԭ�ͣ� BOOL
	 * IdentifyUploadedSingleTag(HANDLE hCom, BYTE* tagID, BYTE* devNo=NULL,
	 * BYTE* antennaNo); ����˵���� ʶ��������ϴ��ĵ�����ǩ�� ����ֵ�� �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @return ����ֵ tagID�� ���ձ�ǩID�������ַ�����������������Ϊ12 devNo��
	 *         �����豸�ŵı�����ַ�����������������Ҫʱ��ΪNULL�� antennaNo��
	 *         �������ߺŵı�����ַ�����������������Ҫʱ��ΪNULL��
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
	 * ���Գɹ���ģʽ��������Ϊ����ʱģʽ���򣢴���ģʽ�����ɶ�ȡ�ɹ��� ����ԭ�ͣ� BOOL
	 * IdentifyUploadedMultiTags(HANDLE hCom, BYTE* tagNum, BYTE* tagIDs, BYTE*
	 * devNos=NULL, BYTE* antennaNos); ����˵���� ʶ��������ϴ��Ķ����ǩ�� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @return ����ֵ�� tagNum�� ���ձ�ǩ���ı�����ַ�������������һ�ο��ܶ�ȡ������ǩ����200�� ��ת��Ϊ10������ tagIDs��
	 *         ���ձ�ǩID�������ַ�����������������Ϊ12 * tagNum �����ַ������� devNos��
	 *         �����豸�ŵ������ַ�����������������Ϊ1 * tagNum������Ҫʱ��ΪNULL�� antennaNos��
	 *         �������ߺŵ������ַ�����������������Ϊ1 * tagNum������Ҫʱ��ΪNULL�� �������10����ǩʱ��
	 *         *tagNum��ֵΪ10��
	 *         tagIDs[0~11]��tagIDs[12~23]������tagIDs[108~119]�ֱ�Ϊ��1������2��
	 *         ������10����ǩ��ID�� devNos[0]��devNos[1]����devNos[9]�ֱ�Ϊ��1������2��������10��ǩ���豸�ţ�
	 *         antennaNos[0]��antennaNos[1]����antennaNos[9]Ϊ��1������2��������10����ǩ�����ߺţ�
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
	 * ����ԭ�ͣ� BOOL ReadTag(HANDLE hCom, BYTE memBank, BYTE address, BYTE length,
	 * BYTE* data, BYTE ReaderAddr); ����˵���� ����ǩ���ݡ� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param memBank
	 *            Ҫ�������򡣸�ֵ���������£� 0���������� 1����EPC�� 2����TID�� 3�����û���
	 * @param address
	 *            Ҫ�������еĵ�ַ��ȡֵΪ��Χ0-7��
	 * @param length
	 *            Ҫ��ȡ�ĳ��ȣ�ȡֵ��Χ��1��8��1Word = 2Byte =
	 *            4λ����˵����memBank=EPC����address+length��ֵ������8
	 *            ��memBank=��������address+length��ֵ������4������
	 * @param readAddr
	 *            ��ͷ��ַ��һ̨�����Ӷ�̨��ͷʱʹ�ã��ӵ�̨��ͷʱ��Ϊ0��
	 * @return �ɹ�ʱ������16���������ַ��� ʧ��ʱ������-1
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	public String readTag(int memBank, int address, int length, int readAddr)
			throws NativeException, IllegalAccessException {
		if (memBank == 1 && (address + length > 8)) {
			throw new NativeException(
					"��ȡEPC������ʱ��address(�������еĵ�ַ)+length(Ҫ��ȡ�ĳ���)��ֵ������8�������������ֵ��");
		}
		if (memBank == 0 && (address + length > 4)) {
			throw new NativeException(
					"��ȡ����������ʱ��address(�������еĵ�ַ)+length(Ҫ��ȡ�ĳ���)��ֵ������4�������������ֵ��");
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
	 * ����ԭ�ͣ� BOOL WriteTagSingleWord(HANDLE hCom, BYTE memBank, BYTE address,
	 * BYTE data1, BYTE data2, BYTE ReaderAddr); ����˵����
	 * ���ǩд��1���֣�2�ֽڣ������ݡ���ע��EPC���ĵ�ַ0��1����д�룩 ����ֵ�� �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param memBank
	 *            Ҫд�����򡣸�ֵ���������£� 0���������� 1����EPC�� 2����TID�� 3�����û���
	 * @param address
	 *            Ҫд�����еĵ�ַ��ȡֵΪ��Χ0-7��memBankΪEPC��ʱ�� 0�� 1����ȡ����
	 * @param data
	 *            Ҫд�����ݵ�4λ16�����ַ��������ֻ����1���ֽ�������2���ֽ��Զ���00����Ϊֱ�ӿ��ƴ�����ַ����ȼ����ͣ�
	 * @param readAddr
	 *            ��ͷ��ַ��һ̨�����Ӷ�̨��ͷʱʹ�ã��ӵ�̨��ͷʱ��Ϊ0��
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
						"��memBankΪEPC��ʱ�� Ҫд�����еĵ�ַaddress���ܵ���0��1��ֻ��ȡֵ2-7��");
			} else {
				// 16�����ַ������ȱ������4
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
							"д���ַ����ȱ���4λ����Ϊ16�����ַ�����������hex����ֵ��");
				}
			}
		} else {
			throw new NativeException(
					"Ҫд������ֵ����д������ֵ��Χ0-3��0������������1����EPC����2����TID����3�����û���");
		}
		System.out.println("writeTagSingleWord:" + result);
		return result;
	}

	/**
	 * ����ԭ�ͣ� BOOL FastWriteTagID(HANDLE hCom, int bytesNum, const BYTE* bytes,
	 * BYTE ReaderAddr); ����˵����
	 * ��д��ǩID����д��ǩ��16�����ַ��ǴӺ�ʼд����ǩ����,�������ȵ��ַ������һλ��д���ǩ����00����д���ǩ���У� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param Port
	 *            ���ھ��
	 * @param byteCount
	 *            Ҫд�����ݵ��ֽ���������Ϊ2��4��6��8��10����12��
	 * @param data
	 *            Ҫд������
	 * @param readAddr
	 *            ��ͷ��ַ��һ̨�����Ӷ�̨��ͷʱʹ�ã��ӵ�̨��ͷʱ��Ϊ0��
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
	 * ����˵������д��ǩ����д��ǩ��16�����ַ��ǴӺ�ʼд����ǩ����,�������ȵ��ַ������һλ��д���ǩ����00����д���ǩ���У� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param memBank
	 *            Ҫд���ǩ�����򡣸�ֵ���������£�0x00���������� 0x01����EPC�� 0x02����TID�� 0x03�����û���
	 * @param address
	 *            Ҫд�������еĵ�ַ��memBankΪEPC��ʱ����ַ��ΧΪ2-7�����WordCountΪ6��
	 *            Ϊ������ʱ����ַ��ΧΪ0-3�����WordCountΪ4�� Ϊ������ʱ��ַַ��ΧΪ0-31�����WordCountΪ8��
	 * @param wordCount
	 *            Ҫд�����ݵĳ��ȣ�����Ϊ��λ��1����Ϊ2���ֽ�
	 * @param data
	 *            Ҫд�������
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
	 * ����ԭ�ͣ� BOOL InitializeTag(HANDLE hCom, BYTE ReaderAddr); ����˵���� ��ʼ����ǩ�� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param readAddr
	 *            ��ͷ��ַ��һ̨�����Ӷ�̨��ͷʱʹ�ã��ӵ�̨��ͷʱ��Ϊ0��
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
	 * ����ʧ�ܣ�ԭ���� ����ԭ�� LockPassWordTag (HANDLE hCom,BYTE passwd1,BYTE
	 * passwd2,BYTE passwd3,BYTE passwd4,BYTE lockType, BYTE ReaderAddr);
	 * ����˵����ͨ����������������ǩ lockTypeֵ �������� 00 UNLOCK USER 01 UNLOCK TID 02 UNLOCK EPC
	 * 03 UNLOCK ACCESS 04 UNLOCK KILL 05 UNLOCK ALL ����ֵ DO NOT UNLOCK
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
			throw new NativeException("д���ַ����ȱ���8λ����Ϊ16�����ַ�����������hex����ֵ��");
		}
		return result;
	}

	/**
	 * ����ʧ�ܣ�ԭ���� ����ԭ��UnlockPassWordTag(HANDLE hCom,BYTE passwd1,BYTE
	 * passwd2,BYTE passwd3,BYTE passwd4,BYTE lockType, BYTE ReaderAddr);
	 * ����˵����ͨ���������������ǩ lockTypeֵ �������� 00 UNLOCK USER 01 UNLOCK TID 02 UNLOCK EPC
	 * 03 UNLOCK ACCESS 04 UNLOCK KILL 05 UNLOCK ALL ����ֵ DO NOT UNLOCK
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
			throw new NativeException("д���ַ����ȱ���8λ����Ϊ16�����ַ�����������hex����ֵ��");
		}
		return result;
	}

	/**
	 * ����ԭ�ͣ� BOOL GetReaderParameters(HANDLE hCom, int addr, int paramNum, BYTE*
	 * params, BYTE ReaderAddr); ����˵���� ��ȡ�����д�������� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param addr
	 *            Ҫ��ѯ�Ķ�д����������ʼ��ַ���������Ķ�Ӧ��ַ�ο�����1-11��
	 * @param paramNum
	 *            Ҫ��ѯ�Ķ�д������������
	 * @param readAddr
	 *            ��ͷ��ַ��һ̨�����Ӷ�̨��ͷʱʹ�ã��ӵ�̨��ͷʱ��Ϊ0��
	 * @return ���ض�Ӧ��ַ����ֵ��ע����һ������Ϊ��ȡ��ַ���ڶ�����������[�Դ�����]�����Ƕ�Ӧ��ַ����ֵ����
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
	 * ����ԭ�ͣ� BOOL SetReaderParameters(HANDLE hCom, int addr, int paramNum, const
	 * BYTE* params, BYTE ReaderAddr); ����˵���� ���ö����д�������� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param addr
	 *            Ҫ��ѯ�Ķ�д����������ʼ��ַ���������Ķ�Ӧ��ַ�ο�����1-11��
	 * @param paramNum
	 *            Ҫ��ѯ�Ķ�д������������
	 * @param params
	 *            ��д�������������ַ
	 * @param readAddr
	 *            ��ͷ��ַ��һ̨�����Ӷ�̨��ͷʱʹ�ã��ӵ�̨��ͷʱ��Ϊ0��
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
	 * ���Բ��ɹ� ����ԭ�ͣ� BOOL ReadTIDByEpcID(HANDLE hCom, const BYTE* bytes,BYTE*
	 * data,BYTE ReaderAddr); ����˵���� ָ����ǩ��EPC�����루12���ֽڣ���ȡ��Ӧ��ǩ��TID����8���ֽڣ� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
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
	 * ����˵����ָ����ǩ��EPC�����루12���ֽڣ���ȡ��Ӧ��ǩ��ͨ��������ģʽ��ʹ�øú����� ����ֵ��
	 * �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param memBank
	 *            Ҫд���ǩ�����򡣸�ֵ���������£�0x00���������� 0x01����EPC�� 0x02����TID�� 0x03�����û���
	 * @param address
	 *            Ҫд�������еĵ�ַ��memBankΪEPC��ʱ����ַ��ΧΪ2-7�����WordCountΪ6��
	 *            Ϊ������ʱ����ַ��ΧΪ0-3�����WordCountΪ4�� Ϊ������ʱ��ַַ��ΧΪ0-31�����WordCountΪ8��
	 * @param wordCount
	 *            Ҫд�����ݵĳ��ȣ�����Ϊ��λ��1����Ϊ2���ֽ�
	 * @param epcID
	 *            EPC ����ָ�룬����12���ֽڵ�EPC����
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
	 * ����ԭ�ͣ� BOOL WriteByEpcID(HANDLE hCom, BYTE memBank, BYTE address, BYTE
	 * WordCount,const BYTE* EpcID,BYTE* data,BYTE ReaderAddr) ����˵����
	 * ��ָ����ǩ��EPC�����루12���ֽڣ��е��ض���ַд��data, ����ֵ�� �ɹ�ʱ����TRUE��1����ʧ��ʱ����FALSE��0��
	 * 
	 * @param memBank
	 *            Ҫд���ǩ�����򡣸�ֵ���������£�0x00���������� 0x01����EPC�� 0x02����TID�� 0x03�����û���
	 * @param address
	 *            Ҫд�������еĵ�ַ��memBankΪEPC��ʱ����ַ��ΧΪ2-7�����WordCountΪ6��
	 *            Ϊ������ʱ����ַ��ΧΪ0-3�����WordCountΪ4�� Ϊ������ʱ��ַַ��ΧΪ0-31�����WordCountΪ8��
	 * @param wordCount
	 *            Ҫд�����ݵĳ��ȣ�����Ϊ��λ��1����Ϊ2���ֽ�
	 * @param epcID
	 *            EPC ����ָ�룬����12���ֽڵ�EPC����
	 * @param data
	 *            Ҫд�������
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
