<% String message = (String) request.getAttribute("lol"); %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file = "header.jsp" %>

<%@include file = "menu.jsp" %>

<h1>Hello, guys!</h1>
<h2><%= message %></h2>
