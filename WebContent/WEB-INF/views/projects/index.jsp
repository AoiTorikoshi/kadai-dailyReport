<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>プロジェクト　一覧</h2>
        <table id="project_list">
            <tbody>
                <tr>
                    <th class="project_title">タイトル</th>
                    <th class="project_content">プロジェクト詳細</th>
                </tr>
                <c:forEach var="project" items="${projects}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="project_title">${project.title}</td>
                        <td class="project_content">${project.content}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${projects_count} 件)<br/>
            <c:forEach var="i" begin="1" end="${((projects_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/projects/index?page=${i}'/>"><c:out value="${i}"/></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value='/projects/new'/>">新規プロジェクトの作成</a></p>

    </c:param>
</c:import>