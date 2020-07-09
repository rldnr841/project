package com.myweb.persistence;

import java.util.List;

import com.myweb.domain.MemberVO;

public interface MemberDAO {
	public int insertMember(MemberVO mvo);//join
	public int selectEmail(String email);//checkEmail
	public MemberVO selectMember(MemberVO mvo);//login
	public List<MemberVO> selectList();
	public MemberVO selectInfo(String email);
	public int updateMember(MemberVO mvo);
	public int deleteMember(String email);
	
}
