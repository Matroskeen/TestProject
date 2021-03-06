package com.matroskeen.helpful;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.matroskeen.beans.FileBean;
import com.matroskeen.beans.UserBean;
import com.matroskeen.dao.UserDAO;



public class FileUploadManager {
	
	public static final String saveDir = "images" + File.separator + "avatars";
	
	public static String uploadFile(int userId, String fileFieldName, HttpServletRequest request, HttpSession session) {
		
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + saveDir;
		String fileName = null;
		
		UserBean user = UserDAO.find(userId);
		
		try {
			for (Part part : request.getParts()) {
				String name = part.getName();
				if (name.equals(fileFieldName)) {
					fileName = extractFileName(part);
					String userAvatar = user.getAvatar();
					if (!fileName.isEmpty()) {
						
						if (userAvatar != null && !userAvatar.isEmpty()) {
							File file = new File(savePath + File.separator + user.getAvatar());
							file.delete();
						}
						part.write(savePath + File.separator + fileName);
					} else if (fileName.isEmpty() && !userAvatar.isEmpty()) {
						return user.getAvatar();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public static String createNewFileName(String fileFullName, String dir) {
		String fileExt = null, fileName = null;
		String destination = dir + File.separator + fileFullName;
		File f = new File(destination);

		if (f.exists()) {
			if (fileFullName.indexOf(".") > 0) {
				fileExt = fileFullName.substring(fileFullName.indexOf("."));
				fileName = fileFullName.substring(0, fileFullName.indexOf("."));
			} else {
				fileExt = "";
				fileName = fileFullName;
			}
			int counter = 0;
			do {
				destination = dir + File.separator + fileName + '_' + counter++ + fileExt;
				f = new File(destination);
			} while (f.exists());
		}
		return destination;
	}

	public static String getFileName(String fileFieldName, Collection<Part> collection) {
		String fileName = "";

		for (Part part : collection) {
			String name = part.getName();
			if (name.equals(fileFieldName)) {
				fileName = extractFileName(part);
			}
		}
		return fileName;
	}

	public static ArrayList<String> uploadFiles(String fileFieldName, String savePath, Collection<Part> collection) {
		String fileName, filePath;
		ArrayList<String> fileNames = new ArrayList<>();
		
		try {
			for (Part part : collection) {
				String name = part.getName();
				if (name.equals(fileFieldName)) {
					fileName = extractFileName(part);
					if (!fileName.isEmpty()) {
						filePath = createNewFileName(fileName, savePath);
						part.write(filePath);
						fileNames.add(filePath.substring(filePath.lastIndexOf(File.separator) + 1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileNames;
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	public static String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}

	/**
	 * Extracts file extension from file name.
	 */
	public static String extractFileExt(String fileName) {
		if (fileName.lastIndexOf(".") > 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return "_blank";
	}

	public static void delete(String fullFilePath) {
		if (fullFilePath != null && !fullFilePath.isEmpty()) {
			File file = new File(fullFilePath);
			 
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
		}
	}

	public static void deleteFiles(ArrayList<FileBean> fileBeans, String path) {
		if (!fileBeans.isEmpty()) {
			for (FileBean bean : fileBeans) {
				
				File file = new File(path + File.separator + bean.getName());
					 
				if (file.delete()) {
					System.out.println(file.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			}
		}
	}
	
	public static boolean fileExist(String basePath, String saveDir, String fileName) {
		
		String filePath = basePath + File.separator + "files" + File.separator + saveDir + File.separator + fileName;
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}
}
