package com.truston.myservice.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogService {

	public static void main(String[] args) {
		while (true) {
            writeLog("Service is running at " + LocalDateTime.now());
            try {
                // Sleep for 60 seconds
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                writeLog("Service interrupted: " + e.getMessage());
                break;
            }
        }
	}
	
    private static void writeLog(String message) {
        try (FileWriter fw = new FileWriter("C:\\Logs\\Truston\\MyService\\service.log", true)) {
            fw.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
