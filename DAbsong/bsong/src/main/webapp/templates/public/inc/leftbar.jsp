<%@page import="library.StringLb"%>
<%@page import="model.bean.SongsJoinCat"%>
<%@page import="model.bean.Songs"%>
<%@page import="model.dao.SongDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDAO"%>
<%@page import="model.bean.Cat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" method="post" action="<%=request.getContextPath() %>/public/search">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Tìm kiếm bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath() %>/templates/public/images/search.jpg"  class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  <%
  	CatDAO objCatDAO = new CatDAO();
  	SongDAO objSDAO = new SongDAO();
  	ArrayList<Cat> listCat = objCatDAO.getItems();
  	ArrayList<SongsJoinCat> listSong = objSDAO.getLS(); 
  	String act = "active2";
  	int cid=0;
  	if(request.getAttribute("cid")!=null){
  		cid = (Integer) request.getAttribute("cid");
  	}
  	if(listCat.size()>0){
  		for(Cat objCat: listCat){
  			int id = objCat.getId();
  %>
    <li ><a class="<%if(cid==id){%><%=act %><%} %>" href="<%=request.getContextPath()%>/<%=StringLb.makeSlug(objCat.getName()) %>-<%=objCat.getId()%>"><%=objCat.getName() %></a></li>
    <%}} %>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  	<%
  	 SongDAO objDAO = new SongDAO();
  	ArrayList<Songs> listSongs = objDAO.getItem();
  	if(listSongs.size()>0){
  		for(Songs objSongs: listSongs){
  	%>
    <li><a href="<%=request.getContextPath() %>/<%=StringLb.makeSlug(objSongs.getCategory().getName()) %>/<%=StringLb.makeSlug(objSongs.getName()) %>-<%=objSongs.getId() %>/<%=objSongs.getCat_id() %>.html"><%=objSongs.getName() %></a><br />
      <span><%=objSongs.getPreview_text() %></span>
      <%}} %>
  </ul>
</div>