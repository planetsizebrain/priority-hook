<%@ include file="/html/portlet/journal/init.jsp" %>

<%
	JournalArticle article = (JournalArticle) request.getAttribute(WebKeys.JOURNAL_ARTICLE);

	String priority = "0";
	if (article != null) {
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), article.getResourcePrimKey());
		priority = Double.toString(assetEntry.getPriority());
	}
%>

<aui:input name="priority" type="text" value="<%= priority %>">
	<aui:validator name="number" />
	<aui:validator name="min">[0]</aui:validator>
</aui:input>