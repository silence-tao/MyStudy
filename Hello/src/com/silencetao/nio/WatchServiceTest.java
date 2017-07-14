package com.silencetao.nio;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * ʹ��WatchService����ļ��仯
 * @author Silence
 *
 */
public class WatchServiceTest {

	public static void main(String[] args) throws Exception {
		//��ȡ�ļ�ϵͳ��WatchService����
		WatchService watchService = FileSystems.getDefault().newWatchService();
		//ΪD:�̸�Ŀ¼·��ע�����
		Paths.get("D:/").register(watchService, StandardWatchEventKinds.ENTRY_CREATE, 
				StandardWatchEventKinds.ENTRY_MODIFY, 
				StandardWatchEventKinds.ENTRY_DELETE);
		while (true) {
			//��ȡ��һ���ļ��仯�¼�
			WatchKey key = watchService.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println(event.context() + "�ļ�������" + event.kind() + "�¼�");
			}
			//����WatchKey
			boolean valid = key.reset();
			//�������ʧ�ܣ��˳�����
			if (!valid) {
				break ;
			}
		}
	}
}