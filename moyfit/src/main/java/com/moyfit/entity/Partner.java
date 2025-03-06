package com.moyfit.entity;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor // ��� �ʵ� ������
@NoArgsConstructor // �⺻ ������
@RequestMapping // getter/setter �޼���
@Getter
@Setter
@ToString // ��ü�� ���¸� ���
public class Partner {

	// ��Ʈ�� ������ȣ
	private long partner_idx;
	// ������ ���̵�
	@NonNull // �ش� �ʵ尡 null�̸� �� �ȴ�->���� �־����
	private String m_id;
	// ���� ���̵�
	@NonNull
	private String partner_id;
	// ���� ���� Y / N
	private String accept_yn;
	// ���� ��¥�� ����
	private Timestamp created_at;
	public static List<Partner> getAllPartners() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setAvg_partner(double d) {
		// TODO Auto-generated method stub
		
	}

}
// <��Ʈ�� ���� ����>