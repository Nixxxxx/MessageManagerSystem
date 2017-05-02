package EpcSDK;

import java.util.HashMap;
import java.util.Set;

import org.xvolks.jnative.exceptions.NativeException;

/**
 * @author zhengwuzhi ����DLL��Ӧ���������� ����DLLʹ��jnative.jarʵ��
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
				System.out.println("�˿ڴ򿪳ɹ��� ����=" + result);
			} else {
				System.out.println("�˿ڴ�ʧ�ܣ�");
			}

			/*************************** ʹ�������ָ���ȡ��ǩ����λ��ͷ�� ************************/
			result = epc.resumeReading(0);
			if (result == 1) {
				System.out.println("��ͷ��λ �ɹ��� ");
			} else {
				System.out.println("��ͷ��λ ʧ�ܣ�");
			}
//
//			String words = "ABAB";
//			result = epc.fastWriteTag(1,2,1, words, 0);
//			System.out.println("fastWriteTag:"+result);
			// ���ǩд��1���֣�2�ֽڣ�������******
			// String singleWord = "1122";
			// result = epc.writeTagSingleWord(1, 2, singleWord, 0);
			// result = writeWords(epc, "7890EEFF3344");

			// /*************************** ����ǩ���� ***************************/
			// String readWord = epc.readTag(1, 2, 6, 0);
			// if ("-1".equals(readWord)) {
			// System.out.println("����ǩ����ʧ�ܣ�");
			// } else {
			// System.out.println("����ǩ���ݳɹ�����ǩ���ݣ�" + readWord);
			// }

			// // ʶ�𵥸���ǩ***
			// HashMap<String, String> singleTagMap = epc.identifySingleTag(0);
			// if (singleTagMap != null) {
			// System.out.println("ʶ�𵥸���ǩ�ɹ�!��ǩID���ݣ�"
			// + singleTagMap.get("tagID").toString() + "  ���ߺŵı�����ַ:"
			// + singleTagMap.get("antennaNo").toString());
			// } else {
			// System.out.println("ʶ�𵥸���ǩʧ��!");
			// }

			// ʶ��������ϴ��ĵ�����ǩ�����Գɹ���ģʽ��������Ϊ����ʱģʽ���򣢴���ģʽ�����ɶ�ȡ�ɹ�����*
			// HashMap<String, String> uploadedSingleTagMap = epc
			// .identifyUploadedSingleTag();
			// if (uploadedSingleTagMap != null) {
			// System.out.println("ʶ��������ϴ��ĵ�����ǩ�ɹ�!��ǩID���ݣ�"
			// + uploadedSingleTagMap.get("tagID").toString()
			// + "   �豸�ŵı�����ַ:"
			// + uploadedSingleTagMap.get("devNo").toString()
			// + "  ���ߺŵı�����ַ:"
			// + uploadedSingleTagMap.get("antennaNo").toString());
			// } else {
			// System.out.println("ʶ��������ϴ��ĵ�����ǩʧ��!");
			// }
			//
			// // ʶ��������ϴ��Ķ����ǩ�����Գɹ���ģʽ��������Ϊ����ʱģʽ���򣢴���ģʽ�����ɶ�ȡ�ɹ�����

//			result = epc.writeByEpcID(1, 2, 2, "E20093666616027518205BDB",
//					"DDEE00FF", 0);
//			System.out.println("result��" + result);

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
				System.out.println("ʶ��������ϴ��Ķ����ǩ�ɹ�!��ǩ��ַ������"
						+ identifyUploadedMultiTagsMap.get("tagNum").toString()
						+ "  \n\t��ǩID�������飺"
						+ tagIdStr
						+ "  \n\t�豸�ŵı�����ַ:"
						+ identifyUploadedMultiTagsMap.get("devNos").toString()
						+ "  \n\t���ߺŵı�����ַ:"
						+ identifyUploadedMultiTagsMap.get("antennaNos")
								.toString());
			} else {
				System.out.println("ʶ��������ϴ��Ķ����ǩʧ��!");
			}

			/*************************** ʹ������ֹͣ��ȡ��ǩ(��һ����ͷֹͣ��ȡ) ***************************/
			result = epc.stopReading(0);
			if (result == 1) {
				System.out.println("������ֹͣ��ȡ �ɹ�");
			} else {
				System.out.println("������ֹͣ��ȡ ʧ��");
			}
			// �رմ���
			 epc.closeComm();
		} catch (NativeException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
		}
	}

	// ��д��ǩID����
	public static int writeWords(EpcSDKDll epc, String words)
			throws NativeException, IllegalAccessException {
		int result = 0;
		// �ж��ַ���Ϊ16�����ַ�����ִ��д�������
		if (EpcSDKDll.matcherStringIsHex(words)) {
			int hexLength = words.length();
			// ���ݿ�д��ǩ���ֽڳ���Ҫ�󣬿���16�����ֽڳ��ȱ���Ϊ4��8��12��16��20����24���Ȳſ�д�룮�����������д�룬ȱ�ٵ��ֽ��Զ���0���䣮
			if (hexLength < 25 && hexLength > 3 && hexLength % 4 == 0) {
				int bytesNum = (int) Math.ceil((double) hexLength / 2); // �ַ����ȳ�2��Ȼ�������
				result = epc.fastWriteTagID(bytesNum, words, 0);
				if (result == 1) {
					System.out.println("��д��ǩд��16��������[" + words + "]�ɹ�!");
				} else {
					System.out.println("��д��ǩд�� ʧ��");
				}
			} else {
				System.out.println("д��16�����ַ����Ȳ���ȷ�����ȱ���Ϊ4��8��12��16��20����24���ַ���");
			}
		} else {
			System.out.println("�����ַ���Ч����������ȷ16�����ַ���");
		}
		return result;
	}
}
