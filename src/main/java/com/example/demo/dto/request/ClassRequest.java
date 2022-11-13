package com.example.demo.dto.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.IBaseDto;
import com.example.demo.entity.MstClass;

import lombok.Data;

@Data
public class ClassRequest implements Serializable, IBaseDto<MstClass> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * クラスID
	 */
	@Min(value=0, message="クラスIDがは、0以上の数値に設定しないといけません")
	private Long classId;
	
	/**
	 * クラス名
	 */
	@NotEmpty(message = "クラス名を入力してください")
	@Size(max = 64, message = "クラス名は64文字以内で入力してください")
	private String className;
	/**
	 * データ状態(0:valid、1:deleted)
	 */
	@Min(value=0, message="データ状態を有効か無効かどちらか選択してください")
	@Max(value=1, message="データ状態を有効か無効かどちらか選択してください")
	private Integer dataStatus;
	
	private Date updatedDate;

	@Override
	public MstClass toEntity() {
		MstClass result = new MstClass();
		if(this.classId == null) {
			BeanUtils.copyProperties(this, result,"classId");			
		}else {
			BeanUtils.copyProperties(this, result);	
		}
		return result;
	}
}
