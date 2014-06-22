package com.dosport.service.attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.ArrayUtils;

import com.dosport.service.exception.FileEmptyException;
import com.dosport.service.exception.FileSizeToLongException;
import com.dosport.service.exception.FileTypeDenyException;
import com.dosport.system.constant.SystemConstants;

/**
 * 附件组件虚类.
 * 
 * @author pwl
 * 
 */
public abstract class AbstractAttachmentComponent {

	// 最大的序列值.
	private final static int MAX_SERIAL = 999999;

	// 可接受的文件类型.
	protected String[] acceptFileTypes = SystemConstants.DEFAULT_ACCEPT_FILE_TYPES;

	// 文件大小限制.
	protected long maxFileSize = SystemConstants.MAX_FILE_SIZE;

	// 文件根路径（不包括文件上级父目录）.
	protected String fileRootPath;

	/**
	 * 获取文件类型.
	 * 
	 * @param fileName
	 * @return
	 */
	public String getFileType(String fileName) {

		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 获取文件全路径.
	 * 
	 * @param basePath
	 * @param filePath
	 * @return
	 */
	public String getFileFullPath(String basePath, String filePath, String fileName) {

		return this.getFileParentPath(basePath, filePath) + "/" + fileName;
	}

	/**
	 * 获取文件父目录（不包括文件名的路径）.
	 * 
	 * @param basePath
	 * @param filePath
	 * @return
	 */
	public String getFileParentPath(String basePath, String filePath) {

		return fileRootPath + "/" + basePath + "/" + filePath;
	}

	/**
	 * 根据时间取得文件保存目录.
	 * 
	 * @param date
	 * @return
	 */
	public String getFilePath(Date date) {

		return this.generateFilePath(date);
	}

	/**
	 * 根据上传时间生成文件保存目录yyyy/MM/dd.
	 * 
	 * @param uploadDate
	 * @return
	 */
	public String generateFilePath(Date uploadDate) {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		return dateformat.format(uploadDate);
	}

	/**
	 * 根据序列值和时间生成 'XXXXXX_YYYYYYYYYYYYY'格式的唯一文件名.
	 * 
	 * @return
	 */
	public String generateFileName() {

		AtomicInteger atomic = new AtomicInteger();
		int serial = atomic.incrementAndGet();
		if (serial >= MAX_SERIAL) {
			atomic.set(0);
		}
		long millsec = System.currentTimeMillis();
		return String.format("%06d_%013d", serial, millsec);
	}

	/**
	 * 判断此文件类型是否允许上传.
	 * 
	 * @param fileName
	 * @param acceptTypes
	 * @throws FileTypeDenyException
	 */
	public void filterFileType(String fileName, String[] acceptTypes) throws FileTypeDenyException {

		String fileType = this.getFileType(fileName).toLowerCase();
		if (ArrayUtils.indexOf(acceptTypes, fileType) < 0) {
			throw new FileTypeDenyException("该文件类型不允许上传");
		}
	}

	/**
	 * 判断文件大小是否超过允许上传的最大值.
	 * 
	 * @param fileSize
	 * @param maxSize
	 * @throws FileSizeToLongException
	 * @throws FileEmptyException
	 */
	public void filterFileSize(long fileSize, long maxSize) throws FileSizeToLongException, FileEmptyException {

		if (fileSize <= 0) {
			throw new FileEmptyException("不能上传空文件");
		}
		if (fileSize > maxSize) {
			throw new FileSizeToLongException("文件大小超过" + maxSize / 1024 / 1024 + "MB");
		}
	}

	/**
	 * 开始写入文件，默认限制类型和大小.
	 * 
	 * @param basePath
	 * @param filePath
	 * @param fileData
	 * @throws IOException
	 * @throws FileSizeToLongException
	 * @throws FileEmptyException
	 * @throws FileTypeDenyException
	 */
	public String writeFile(String basePath, String filePath, File fileData) throws IOException,
			FileSizeToLongException, FileEmptyException, FileTypeDenyException {

		String fileSaveName = this.generateFileName() + "." + this.getFileType(fileData.getName());
		this.writeFile(basePath, filePath, fileSaveName, fileData, true, true);
		return fileSaveName;
	}

	/**
	 * 开始写入文件.
	 * 
	 * @param basePath
	 * @param filePath
	 * @param fileSaveName
	 * @param fileData
	 * @param filterSize
	 * @param filterType
	 * @throws IOException
	 * @throws FileSizeToLongException
	 * @throws FileEmptyException
	 * @throws FileTypeDenyException
	 */
	public void writeFile(String basePath, String filePath, String fileSaveName, File fileData, boolean filterSize,
			boolean filterType) throws IOException, FileSizeToLongException, FileEmptyException, FileTypeDenyException {

		if (filterSize) {
			this.filterFileSize(fileData.length(), this.maxFileSize);
		}
		if (filterType) {
			this.filterFileType(fileSaveName, this.acceptFileTypes);
		}
		String fileFullPath = this.getFileFullPath(basePath, filePath, fileSaveName);
		File file = new File(fileFullPath);
		// 目录是否存在
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}

		FileOutputStream fos = new FileOutputStream(fileFullPath);
		FileInputStream fis = new FileInputStream(fileData);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		fis.close();
	}

	public void deleteFile(String basePath, String filePath, String fileSaveName) {

		String fileFullPath = this.getFileFullPath(basePath, filePath, fileSaveName);
		File file = new File(fileFullPath);
		if (file.exists()) {
			file.delete();
		}
	}

	public String[] getAcceptFileTypes() {
		return acceptFileTypes;
	}

	public void setAcceptFileTypes(String[] acceptFileTypes) {
		this.acceptFileTypes = acceptFileTypes;
	}

	public long getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String getFileRootPath() {
		return fileRootPath;
	}

	public void setFileRootPath(String fileRootPath) {
		this.fileRootPath = fileRootPath;
	}

}
