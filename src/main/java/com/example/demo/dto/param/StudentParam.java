package com.example.demo.dto.param;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class StudentParam {
	/**
	 * 学生ID
	 *
	 */
	@Min(value=0, message="学生IDは、0以上の数値に設定しないといけません")
	protected Long studentId;
	/**
	 * 名前
	 */
	@NotEmpty(message = "学生名が未入力です")
	@Size(max = 64, message = "学生名は64文字以内で入力してください")
	protected String studentName;
	/**
	 * 性別（1:male、2:female）
	 */
	@NotNull(message= "性別が未入力です")
	@Min(value=1, message="性的を男か女かどちらか選択してください")
	@Max(value=3, message="性的を男か女かどちらか選択してください")
	protected Integer studentSex;
	/**
	 * 年齢
	 */
	@NotNull(message= "年齢が未入力です")
	@Min(value=6, message="年齢を6～100歳の範囲に入力してください")
	@Max(value=100, message="年齢を6～100歳の範囲に入力してください")
	protected Integer studentAge;
	
	/**
	 * データ状態(0:valid、1:deleted)
	 */
	@NotNull(message= "データ状態が未入力です")
	@Min(value=0, message="データ状態を有効か無効かどちらか選択してください")
	@Max(value=1, message="データ状態を有効か無効かどちらか選択してください")
	protected Integer dataStatus;
	
	/**
	 * 登録日時
	 */
	protected Date registeredDate;
	
	/**
	 * 更新日時
	 */
	protected Date updatedDate;
}
