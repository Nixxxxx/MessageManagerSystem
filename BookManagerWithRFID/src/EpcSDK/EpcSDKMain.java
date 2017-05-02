package EpcSDK;

import java.util.HashMap;
import java.util.Set;

import org.xvolks.jnative.exceptions.NativeException;

/**
 * @author zhengwuzhi 调用DLL对应方法的例子 调用DLL使用jnative.jar实现
 * 
 */
public class EpcSDKMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			int result = 0;
			EpcSDKDll epc = new EpcSDKDll();
			result = epc.openComm(4);
			if (result != -1) {
				System.out.println("端口打开成功！ 串号=" + result);
			} else {
				System.out.println("端口打开失败！");
			}

			/*************************** 使读卡器恢复读取标签（复位读头） ************************/
			result = epc.resumeReading(0);
			if (result == 1) {
				System.out.println("读头复位 成功！ ");
			} else {
				System.out.println("读头复位 失败！");
			}
//
//			String words = "ABAB";
//			result = epc.fastWriteTag(1,2,1, words, 0);
//			System.out.println("fastWriteTag:"+result);
			// 向标签写入1个字（2字节）的内容******
			// String singleWord = "1122";
			// result = epc.writeTagSingleWord(1, 2, singleWord, 0);
			// result = writeWords(epc, "7890EEFF3344");

			// /*************************** 读标签内容 ***************************/
			// String readWord = epc.readTag(1, 2, 6, 0);
			// if ("-1".equals(readWord)) {
			// System.out.println("读标签内容失败！");
			// } else {
			// System.out.println("读标签内容成功，标签内容：" + readWord);
			// }

			// // 识别单个标签***
			// HashMap<String, String> singleTagMap = epc.identifySingleTag(0);
			// if (singleTagMap != null) {
			// System.out.println("识别单个标签成功!标签ID数据："
			// + singleTagMap.get("tagID").toString() + "  天线号的变量地址:"
			// + singleTagMap.get("antennaNo").toString());
			// } else {
			// System.out.println("识别单个标签失败!");
			// }

			// 识别读卡器上传的单个标签（测试成功，模式必须设置为＂定时模式＂或＂触发模式＂方可读取成功．）*
			// HashMap<String, String> uploadedSingleTagMap = epc
			// .identifyUploadedSingleTag();
			// if (uploadedSingleTagMap != null) {
			// System.out.println("识别读卡器上传的单个标签成功!标签ID数据："
			// + uploadedSingleTagMap.get("tagID").toString()
			// + "   设备号的变量地址:"
			// + uploadedSingleTagMap.get("devNo").toString()
			// + "  天线号的变量地址:"
			// + uploadedSingleTagMap.get("antennaNo").toString());
			// } else {
			// System.out.println("识别读卡器上传的单个标签失败!");
			// }
			//
			// // 识别读卡器上传的多个标签（测试成功，模式必须设置为＂定时模式＂或＂触发模式＂方可读取成功．）

//			result = epc.writeByEpcID(1, 2, 2, "E20093666616027518205BDB",
//					"DDEE00FF", 0);
//			System.out.println("result：" + result);

			HashMap<String, Object> identifyUploadedMultiTagsMap = epc
					.identifyUploadedMultiTags();

			if (identifyUploadedMultiTagsMap != null) {
				@SuppressWarnings("unchecked")
				Set<String> tagIds = (Set<String>) identifyUploadedMultiTagsMap
						.get("tagIDs");
				String tagIdStr = "";
				for (String tagId : tagIds) {
					tagIdStr += tagId + ",";
				}
				System.out.println("识别读卡器上传的多个标签成功!标签地址数量："
						+ identifyUploadedMultiTagsMap.get("tagNum").toString()
						+ "  \n\t标签ID数据数组："
						+ tagIdStr
						+ "  \n\t设备号的变量地址:"
						+ identifyUploadedMultiTagsMap.get("devNos").toString()
						+ "  \n\t天线号的变量地址:"
						+ identifyUploadedMultiTagsMap.get("antennaNos")
								.toString());
			} else {
				System.out.println("识别读卡器上传的多个标签失败!");
			}

			/*************************** 使读卡器停止读取标签(第一个读头停止读取) ***************************/
			result = epc.stopReading(0);
			if (result == 1) {
				System.out.println("读卡器停止读取 成功");
			} else {
				System.out.println("读卡器停止读取 失败");
			}
			// 关闭串口
			 epc.closeComm();
		} catch (NativeException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
		}
	}

	// 快写标签ID操作
	public static int writeWords(EpcSDKDll epc, String words)
			throws NativeException, IllegalAccessException {
		int result = 0;
		// 判断字符串为16进制字符串才执行写入操作．
		if (EpcSDKDll.matcherStringIsHex(words)) {
			int hexLength = words.length();
			// 根据快写标签的字节长度要求，控制16进制字节长度必须为4，8，12，16，20，或24长度才可写入．如果不按长度写入，缺少的字节自动以0补充．
			if (hexLength < 25 && hexLength > 3 && hexLength % 4 == 0) {
				int bytesNum = (int) Math.ceil((double) hexLength / 2); // 字符长度除2，然后凑整；
				result = epc.fastWriteTagID(bytesNum, words, 0);
				if (result == 1) {
					System.out.println("快写标签写入16进制数据[" + words + "]成功!");
				} else {
					System.out.println("快写标签写入 失败");
				}
			} else {
				System.out.println("写入16进制字符长度不正确，长度必须为4，8，12，16，20，或24个字符！");
			}
		} else {
			System.out.println("输入字符无效，请输入正确16进制字符！");
		}
		return result;
	}
}
