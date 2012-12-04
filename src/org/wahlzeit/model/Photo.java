/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import java.sql.*;
import java.net.*;

import org.wahlzeit.services.*;
import org.wahlzeit.utils.*;

/**
 * A photo represents a user-provided (uploaded) photo.
 * 
 * @author dirkriehle
 *
 */
public class Photo extends DataObject {

	/**
	* 
	*/
	public static final String IMAGE = "image";
	public static final String THUMB = "thumb";
	public static final String LINK = "link";
	public static final String PRAISE = "praise";
	public static final String NO_VOTES = "noVotes";
	public static final String CAPTION = "caption";
	public static final String DESCRIPTION = "description";
	public static final String KEYWORDS = "keywords";

	public static final String TAGS = "tags";

	public static final String STATUS = "status";
	public static final String IS_INVISIBLE = "isInvisible";
	public static final String UPLOADED_ON = "uploadedOn";
	
	/**
	* 
	*/
	public static final int MAX_PHOTO_WIDTH = 420;
	public static final int MAX_PHOTO_HEIGHT = 600;
	public static final int MAX_THUMB_PHOTO_WIDTH = 105;
	public static final int MAX_THUMB_PHOTO_HEIGHT = 150;
	
	/**
	* 
	*/
	protected PhotoId id = null;
	
	/**
	* 
	*/
	protected int ownerId = 0;
	protected String ownerName;

	/**
	* 
	*/
	protected boolean ownerNotifyAboutPraise = false;
	protected EmailAddress ownerEmailAddress = EmailAddress.NONE;
	protected Language ownerLanguage = Language.ENGLISH;
	protected URL ownerHomePage;
	
	/**
	* 
	*/
	protected int width;
	protected int height;
	protected PhotoSize maxPhotoSize = PhotoSize.MEDIUM; // derived
	
	/**
	* 
	*/
	protected Tags tags = Tags.EMPTY_TAGS;

	/**
	* 
	*/
	protected PhotoStatus status = PhotoStatus.VISIBLE;
	
	/**
	* 
	*/
	protected int praiseSum = 10;
	protected int noVotes = 1;
	
	/**
	* 
	*/
	protected long creationTime = System.currentTimeMillis();
	
	/**
	* 
	*/
	public Photo() {
		assert(PhotoId.getNextId()!=null):" PhotoId.getNextId() should not be null";
		id = PhotoId.getNextId();
		incWriteCount();
	}
	
	/**
	* 
	* @methodtype constructor
	*/
	public Photo(PhotoId myId) {
		assert(myId!=null):"myId should not be empty";
		id = myId;
		
		incWriteCount();
	}
	
	/**
	* 
	* @methodtype constructor
	*/
	public Photo(ResultSet rset) throws SQLException {
		assert(rset!=null):"rset should not be empty";
		readFrom(rset);
	}

	/**
	* 
	* @methodtype get
	*/
	public String getIdAsString() {
		assert(String.valueOf(id.asInt())!=null):"String value of id should not be null";
		return String.valueOf(id.asInt());
	}
	
	/**
	* 
	*/
	public void readFrom(ResultSet rset) throws SQLException {
		assert(rset!=null):"rset should not be empty";
		id = PhotoId.getId(rset.getInt("id"));

		ownerId = rset.getInt("owner_id");
		ownerName = rset.getString("owner_name");
		
		ownerNotifyAboutPraise = rset.getBoolean("owner_notify_about_praise");
		ownerEmailAddress = EmailAddress.getFromString(rset.getString("owner_email_address"));
		ownerLanguage = Language.getFromInt(rset.getInt("owner_language"));
		ownerHomePage = StringUtil.asUrl(rset.getString("owner_home_page"));

		width = rset.getInt("width");
		height = rset.getInt("height");

		tags = new Tags(rset.getString("tags"));

		status = PhotoStatus.getFromInt(rset.getInt("status"));
		praiseSum = rset.getInt("praise_sum");
		noVotes = rset.getInt("no_votes");

		creationTime = rset.getLong("creation_time");

		maxPhotoSize = PhotoSize.getFromWidthHeight(width, height);
	}
	
	/**
	* 
	*/
	public void writeOn(ResultSet rset) throws SQLException {
		assert(rset!=null):"rset should not be empty";
		rset.updateInt("id", id.asInt());
		rset.updateInt("owner_id", ownerId);
		rset.updateString("owner_name", ownerName);
		rset.updateBoolean("owner_notify_about_praise", ownerNotifyAboutPraise);
		rset.updateString("owner_email_address", ownerEmailAddress.asString());
		rset.updateInt("owner_language", ownerLanguage.asInt());
		rset.updateString("owner_home_page", ownerHomePage.toString());
		rset.updateInt("width", width);
		rset.updateInt("height", height);
		rset.updateString("tags", tags.asString());
		rset.updateInt("status", status.asInt());
		rset.updateInt("praise_sum", praiseSum);
		rset.updateInt("no_votes", noVotes);
		rset.updateLong("creation_time", creationTime);		
	}

	/**
	* 
	*/
	public void writeId(PreparedStatement stmt, int pos) throws SQLException {
		assert(stmt!=null && pos>=1):"stmt should not be empty and pos should be greater equal to 0";
		stmt.setInt(pos, id.asInt());
	}
	
	/**
	* 
	* @methodtype get
	*/
	public PhotoId getId() {
		assert(id!=null):"Photo id should not be empty";
		return id;
	}
	
	/**
	* 
	* @methodtype get
	*/
	public int getOwnerId() {
		assert(ownerId>=0):"ownerId should greater equal to 0";
		return ownerId;
	}
	
	/**
	* 
	* @methodtype set
	*/
	public void setOwnerId(int newId) {
		ownerId = newId;
		incWriteCount();
		assert(ownerId==newId):"ownerId should be equal to newId";
	}
	
	/**
	* 
	* @methodtype get
	*/
	public String getOwnerName() {
		assert(ownerName!=null):"ownerName should not be empty";
		return ownerName;
	}
	
	/**
	* 
	* @methodtype set
	*/
	public void setOwnerName(String newName) {
		ownerName = newName;
		incWriteCount();
		assert(ownerName==newName):"ownerName should be equal to newName";
	}
	
	/**
	* 
	* @methodtype get
	*/
	public String getSummary(ModelConfig cfg) {
		assert(cfg!=null):"cfg should not be empty";
		return cfg.asPhotoSummary(ownerName);
	}

	/**
	* 
	* @methodtype get
	*/
	public String getCaption(ModelConfig cfg) {
		assert(cfg!=null):"cfg should not be empty";
		return cfg.asPhotoCaption(ownerName, ownerHomePage);
	}

	/**
	* 
	* @methodtype get
	*/
	public boolean getOwnerNotifyAboutPraise() {
		return ownerNotifyAboutPraise;
	}
	
	/**
	* 
	* @methodtype set
	*/
	public void setOwnerNotifyAboutPraise(boolean newNotifyAboutPraise) {
		ownerNotifyAboutPraise = newNotifyAboutPraise;
		incWriteCount();
		assert(ownerNotifyAboutPraise==newNotifyAboutPraise):"ownerNotifyAboutPraise and newNotifyAboutPraise should have the same boolean value";
	}

	/**
	* 
	* @methodtype get
	*/
	public EmailAddress getOwnerEmailAddress() {
		assert(ownerEmailAddress!=null):"ownerEmailAddress should not be empty";
		return ownerEmailAddress;
	}
	
	/**
	* 
	* @methodtype set
	*/
	public void setOwnerEmailAddress(EmailAddress newEmailAddress) {
		ownerEmailAddress = newEmailAddress;
		incWriteCount();
		assert(ownerEmailAddress==newEmailAddress):"ownerEmailAddress and newEmailAddress should have the same value";
	}

	/**
	* 
	*/
	public Language getOwnerLanguage() {
		assert(ownerEmailAddress!=null):"ownerEmailAddress should not be empty";
		return ownerLanguage;
	}
	
	/**
	* 
	*/
	public void setOwnerLanguage(Language newLanguage) {
		ownerLanguage = newLanguage;
		incWriteCount();
		assert(ownerLanguage==newLanguage):"ownerLanguage and newLanguage should have the same value";
	}

	/**
	* 
	* @methodtype get
	*/
	public URL getOwnerHomePage() {
		assert(ownerEmailAddress!=null):"ownerEmailAddress should not be empty";
		return ownerHomePage;
	}
	
	/**
	* 
	* @methodtype set
	*/
	public void setOwnerHomePage(URL newHomePage) {
		ownerHomePage = newHomePage;
		incWriteCount();
		assert(ownerHomePage==newHomePage):"ownerLanguage and newHomePage should have the same  value";
	}
	
	/**
	* 
	* @methodtype boolean-query
	*/
	public boolean hasSameOwner(Photo photo) {
		assert(photo!=null):"photo should not be empty";
		return photo.getOwnerEmailAddress().equals(ownerEmailAddress);
	}

	/**
	* 
	* @methodtype boolean-query
	*/
	public boolean isWiderThanHigher() {
		assert(height>=0):"height should be greater equal to 0";
		assert(width>=0):"width should be greater equal to 0";
		return (height * MAX_PHOTO_WIDTH) < (width * MAX_PHOTO_HEIGHT);
	}
	
	/**
	* 
	* @methodtype get
	*/
	public int getWidth() {
		assert(width>=0):"width should be greater equal to 0";
		return width;
	}
	
	/**
	* 
	* @methodtype get
	*/
	public int getHeight() {
		assert(height>=0):"height should be greater equal to 0";
		return height;
	}
	
	/**
	* 
	* @methodtype get
	*/
	public int getThumbWidth() {
		//no need for assertion here, width and height are checked in isWiderThanHigher()
		//and isWiderThanHigher,MAX_THUMB_PHOTO_HEIGHT are class variables
		return isWiderThanHigher() ? MAX_THUMB_PHOTO_WIDTH : (width * MAX_THUMB_PHOTO_HEIGHT / height);
	}
	
	/**
	* 
	* @methodtype get
	*/
	public int getThumbHeight() {
		//no need for assertion here, width and height are checked in isWiderThanHigher()
		//and isWiderThanHigher,MAX_THUMB_PHOTO_HEIGHT are class variables
		return isWiderThanHigher() ? (height * MAX_THUMB_PHOTO_WIDTH / width) : MAX_THUMB_PHOTO_HEIGHT;
	}
	
	/**
	* 
	* @methodtype set
	*/
	public void setWidthAndHeight(int newWidth, int newHeight) {
		width = newWidth;
		height = newHeight;

		maxPhotoSize = PhotoSize.getFromWidthHeight(width, height);

		incWriteCount();
		assert(width==newWidth):"width and newWidth should have the same  value";
		assert(height==newHeight):"height and newHeight should have the same  value";
		assert(PhotoSize.getFromWidthHeight(width, height)!=null):"PhotoSize.getFromWidthHeight(width, height) should not be empty";
	}
	
	/**
	* Can this photo satisfy provided photo size?
	* 
	* @methodtype boolean-query
	*/
	public boolean hasPhotoSize(PhotoSize size) {
		assert(maxPhotoSize.asInt()>=size.asInt()):"width and newWidth should have the same  value";
		return maxPhotoSize.asInt() >= size.asInt();
	}
	
	/**
	* 
	* @methodtype get
	*/
	public PhotoSize getMaxPhotoSize() {
		assert(maxPhotoSize!=null):"maxPhotoSize should not be empty";
		return maxPhotoSize;
	}
	
	/**
	* 
	* @methodtype get
	*/
	public double getPraise() {
		assert((double) praiseSum / noVotes>=0):"praiseSum/noVotes should be greater equal to 0";
		return (double) praiseSum / noVotes;
	}
	
	/**
	* 
	* @methodtype get
	*/
	public String getPraiseAsString(ModelConfig cfg) {
		assert(cfg!=null):"cfg should not be empty";
		return cfg.asPraiseString(getPraise());
	}
	
	/**
	* 
	*/
	public void addToPraise(int value) {
		assert(value>=0):"value should be greated equal to 0";
		praiseSum += value;
		noVotes += 1;
		incWriteCount();
		
	}
	
	/**
	* 
	* @methodtype boolean-query
	*/
	public boolean isVisible() {
		assert(status!=null):"status should not be empty";
		return status.isDisplayable();
	}
	
	/**
	* 
	* @methodtype get
	*/
	public PhotoStatus getStatus() {
		assert(status!=null):"status should not be empty";
		return status;
	}
	
	/**
	* 
	* @methodtype set
	*/
	public void setStatus(PhotoStatus newStatus) {
		status = newStatus;
		incWriteCount();
		assert(status==newStatus):"status and newStatus should have the same value";
	}
	
	/**
	* 
	* @methodtype boolean-query
	*/
	public boolean hasTag(String tag) {
		assert(tag!=null):"tag should not be empty";
		return tags.hasTag(tag);
	}
	
	/**
	* 
	* @methodtype get
	*/
	public Tags getTags() {
		assert(tags!=null):"tags should not be empty";
		return tags;
	}

	/**
	* 
	* @methodtype set
	*/
	public void setTags(Tags newTags) {
		tags = newTags;
		incWriteCount();
		assert(tags==newTags):"tags and newTags should have the same value";
	}
	
	/**
	* 
	* @methodtype get
	*/
	public long getCreationTime() {
		assert(creationTime>=0):"creationTime should belarger equal to 0";
		return creationTime;
	}
	
}
