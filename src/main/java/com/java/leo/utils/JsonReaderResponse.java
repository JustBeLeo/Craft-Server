package com.java.leo.utils;

import org.springframework.data.domain.Page;

import java.util.List;


/**

 * 
 * @param <T>
 *          
 */
public class JsonReaderResponse<T> {

	private List<T> records;

	private boolean success;

	private Long total;
	
	private String message;
	
	private int pageCount;
	
	private int currentPage;

	/**
	 * Creates a {@link #success}ful JsonReaderResponse with the provided
	 * {@link #records}.
	 * 
	 * @param records
	 */
	public JsonReaderResponse(Page<T> page) {
		this.records = page.getContent();
		success = true;
		total = page.getTotalElements();
	}
	
	public JsonReaderResponse(Page<T> page, boolean isSuccess) {
		this.records = page.getContent();
		success = isSuccess;
		total = page.getTotalElements();
	}
	
	public JsonReaderResponse(Page<T> page, boolean isSuccess, String msg) {
		this.records = page.getContent();
		success = isSuccess;
		total = page.getTotalElements();
		message = msg;
	}
	
	public JsonReaderResponse(List<T> objectsToConvertToRecords, int totalCount) {
		this.records = objectsToConvertToRecords;
		success = true;
		total = Integer.valueOf(totalCount).longValue();
	}
	
	public JsonReaderResponse(List<T> objectsToConvertToRecords, Long totalCount) {
		this.records = objectsToConvertToRecords;
		success = true;
		total = totalCount;
	}
	
	public JsonReaderResponse(List<T> objectsToConvertToRecords, Long totalCount, String messageProperty) {
		this.records = objectsToConvertToRecords;
		success = true;
		total = totalCount;
		message = messageProperty;
	}

	public JsonReaderResponse(List<T> objectsToConvertToRecords, boolean succ, Long totalCount, String messageProperty) {
		this.records = objectsToConvertToRecords;
		success = succ;
		total = totalCount;
		message = messageProperty;
	}

	/**
	 * Creates a {@link #success}ful JsonReaderResponse with the provided
	 * {@link #records}.
	 * 
	 * @param records
	 */
	public JsonReaderResponse(Page<T> page, int pageSize, int currentPage) {
		// page为空判断由lyx添加
		if(page == null) {
			this.records = null;
			success = false;
			total = (long) 0;
		} else {
			this.records = page.getContent();
			success = true;
			total = page.getTotalElements();
			if(page.getTotalElements() % pageSize != 0) {
				this.pageCount = (int) (page.getTotalElements() / pageSize + 1);
		    } else {
		    	this.pageCount = (int) (page.getTotalElements() / pageSize);
		    }
			this.currentPage = currentPage;
		}
	}
	/**
	 * Creates an un{@link #success}ful JsonReaderResponse with null
	 * {@link #records}. This signals the case where the
	 * client established a connection with the server, but the server couldn't
	 * fulfill it (e.g., user doesn't have proper user credentials).
	 * 
	 * @param records
	 */
	public JsonReaderResponse() {
		this.records = null;
		success = false;
	}

	
	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long totalCount) {
		this.total = totalCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}