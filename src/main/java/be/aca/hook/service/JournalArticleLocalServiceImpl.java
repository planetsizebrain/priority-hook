package be.aca.hook.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalService;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceWrapper;

import java.io.File;
import java.util.Locale;
import java.util.Map;

public class JournalArticleLocalServiceImpl extends JournalArticleLocalServiceWrapper {

	private static final String PRIORITY = "priority";

	public JournalArticleLocalServiceImpl(JournalArticleLocalService journalArticleLocalService) {
		super(journalArticleLocalService);
	}

	@Override
	public JournalArticle addArticle(long userId, long groupId, long folderId, long classNameId, long classPK, String articleId, boolean autoArticleId, double version, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, String content, String type, String ddmStructureKey, String ddmTemplateKey, String layoutUuid, int displayDateMonth, int displayDateDay, int displayDateYear, int displayDateHour, int displayDateMinute, int expirationDateMonth, int expirationDateDay, int expirationDateYear, int expirationDateHour, int expirationDateMinute, boolean neverExpire, int reviewDateMonth, int reviewDateDay, int reviewDateYear, int reviewDateHour, int reviewDateMinute, boolean neverReview, boolean indexable, boolean smallImage, String smallImageURL, File smallImageFile, Map<String, byte[]> images, String articleURL, ServiceContext serviceContext) throws PortalException, SystemException {
		JournalArticle article = super.addArticle(userId, groupId, folderId, classNameId, classPK, articleId, autoArticleId, version, titleMap, descriptionMap, content, type, ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay, displayDateYear, displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay, expirationDateYear, expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL, smallImageFile, images, articleURL, serviceContext);
		setPriority(article, serviceContext);

		return article;
	}

	@Override
	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, String content, String layoutUuid, ServiceContext serviceContext) throws PortalException, SystemException {
		JournalArticle article = super.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, content, layoutUuid, serviceContext);
		setPriority(article, serviceContext);

		return article;
	}

	@Override
	public JournalArticle updateArticle(long userId, long groupId, long folderId, String articleId, double version, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, String content, String type, String ddmStructureKey, String ddmTemplateKey, String layoutUuid, int displayDateMonth, int displayDateDay, int displayDateYear, int displayDateHour, int displayDateMinute, int expirationDateMonth, int expirationDateDay, int expirationDateYear, int expirationDateHour, int expirationDateMinute, boolean neverExpire, int reviewDateMonth, int reviewDateDay, int reviewDateYear, int reviewDateHour, int reviewDateMinute, boolean neverReview, boolean indexable, boolean smallImage, String smallImageURL, File smallImageFile, Map<String, byte[]> images, String articleURL, ServiceContext serviceContext) throws PortalException, SystemException {
		JournalArticle article = super.updateArticle(userId, groupId, folderId, articleId, version, titleMap, descriptionMap, content, type, ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay, displayDateYear, displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay, expirationDateYear, expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL, smallImageFile, images, articleURL, serviceContext);
		setPriority(article, serviceContext);

		return article;
	}

	private double getPriority(ServiceContext serviceContext) {
		String priority = (String) serviceContext.getAttribute(PRIORITY);

		return priority != null ? Double.parseDouble(priority) : 0;
	}

	private void setPriority(JournalArticle article, ServiceContext serviceContext) throws SystemException, PortalException {
		double priority = getPriority(serviceContext);
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), article.getResourcePrimKey());
		assetEntry.setPriority(priority);
		AssetEntryLocalServiceUtil.updateAssetEntry(assetEntry);
	}
}