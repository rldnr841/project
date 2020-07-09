<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>
<!-- Content section -->
<section class="py-5">
	<div class="container">
		<h1>회원 목록</h1>
		<table class="table table-hover">
    <thead>
      <tr class="table-info">
        <th>Email</th>
        <th>NickName</th>
        <th>Register Date</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${mList }" var="mvo">    
      <tr>
        <td>${mvo.email }</td>
        <td><a href="/member/detail?email=${mvo.email }">${mvo.nickname }</a></td>
        <td>${mvo.regd8 }</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
	</div>
</section>
<jsp:include page="../common/footer.jsp"></jsp:include>