package com.moyfit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.NonFinal;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Party {

	// ���� ���� ��ȣ
	private int p_idx;
	// ���� ���̵�
	@NonNull
	private String m_id;
	// ���� �̸�
	@NonNull
	private String p_name;
	// ������ �ּ�
	@NonNull
	private String p_addr;
	// ������ ����
	private double p_lat;
	// ������ �浵
	private double p_lon;
	// ���� ���� �Ұ�
	private String p_info;
	// ���� ��ǥ ����
	private String p_photo;
	// ���� ������ ���� ��
	@NonNull
	private int p_limit;
	// ���� � ����
	@NonNull
	private String p_sport;
	// ���� ��ұ����� �Ÿ�
	private double distance;
	// ���� ����
	private char p_accepted;
	// ��û ������ �ο�
	private int p_personnel;

}
