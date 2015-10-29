/**
 * AndTinder v0.1 for Android
 *
 * @Author: Enrique López Mañas <eenriquelopez@gmail.com>
 * http://www.lopez-manas.com
 *
 * TAndTinder is a native library for Android that provide a
 * Tinder card like effect. A card can be constructed using an
 * image and displayed with animation effects, dismiss-to-like
 * and dismiss-to-unlike, and use different sorting mechanisms.
 *
 * AndTinder is compatible with API Level 13 and upwards
 *
 * @copyright: Enrique López Mañas
 * @license: Apache License 2.0
 */

package com.andtinder.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.net.URI;

public class CardModel {

	private String   title;
	private Uri geoLocation;
	private String distance;
	private Drawable cardImageDrawable;
	private Drawable cardLikeImageDrawable;
	private Drawable rating;
	private Drawable cardDislikeImageDrawable;

    private OnCardDismissedListener mOnCardDismissedListener = null;

    private OnClickListener mOnClickListener = null;

    public interface OnCardDismissedListener {
        void onLike();
        void onDislike();
    }

    public interface OnClickListener {
        void OnClickListener();
    }

	public CardModel() {
		this(null, null, null,(Drawable)null,(Drawable)null);
	}

	public CardModel(String title, String distance, Uri geoLocation,Drawable rating, Drawable cardImage) {
		this.title = title;
		this.distance = distance;
		this.cardImageDrawable = cardImage;
		this.rating = rating;
        this.geoLocation = geoLocation;
	}

	public CardModel(String title, String distance, Bitmap rating, Bitmap cardImage) {
		this.title = title;
		this.distance = distance;
		this.cardImageDrawable = new BitmapDrawable(null, cardImage);
		this.rating = new BitmapDrawable(null, rating);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Drawable getCardImageDrawable() {
		return cardImageDrawable;
	}

	public void setCardImageDrawable(Drawable cardImageDrawable) {
		this.cardImageDrawable = cardImageDrawable;
	}

	public void setRating(Drawable rating) {
		this.rating = rating;
	}

	public Drawable getRating(){
		return rating;
	}

	public Drawable getCardLikeImageDrawable() {
		return cardLikeImageDrawable;
	}

	public void setCardLikeImageDrawable(Drawable cardLikeImageDrawable) {
		this.cardLikeImageDrawable = cardLikeImageDrawable;
	}

	public Drawable getCardDislikeImageDrawable() {
		return cardDislikeImageDrawable;
	}

	public void setCardDislikeImageDrawable(Drawable cardDislikeImageDrawable) {
		this.cardDislikeImageDrawable = cardDislikeImageDrawable;
	}

    public void setOnCardDismissedListener( OnCardDismissedListener listener ) {//sets monocard
        this.mOnCardDismissedListener = listener;
    }

    public OnCardDismissedListener getOnCardDismissedListener() {//entry point of CardContainer
       return this.mOnCardDismissedListener;
    }


    public void setOnClickListener( OnClickListener listener ) {
        this.mOnClickListener = listener;
    }

    public OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }
}
