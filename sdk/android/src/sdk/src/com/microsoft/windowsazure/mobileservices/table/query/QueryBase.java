/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
Apache 2.0 License
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */
package com.microsoft.windowsazure.mobileservices.table.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.microsoft.windowsazure.mobileservices.MobileServiceException;

import android.util.Pair;

/**
 * Class that represents a query to a table, where E is the callback class to
 * use when executing the query
 */
public class QueryBase implements Query {

	/**
	 * The main text of the query
	 */
	private QueryNode mQueryNode;

	/**
	 * Indicates if the query should include the inlinecount property
	 */
	private boolean mHasInlineCount = false;

	/**
	 * Query ordering to use
	 */
	private List<Pair<String, QueryOrder>> mOrderBy = new ArrayList<Pair<String, QueryOrder>>();

	/**
	 * Query projection to use
	 */
	private List<String> mProjection = null;

	/**
	 * User-defined properties to use
	 */
	private List<Pair<String, String>> mUserDefinedParameters = new ArrayList<Pair<String, String>>();

	/**
	 * Top rows to retrieve
	 */
	private int mTop = -1;

	/**
	 * Rows to skip
	 */
	private int mSkip = -1;

	private String mTableName = "";

	/**
	 * Creates an empty QueryBase.
	 */
	public QueryBase() {

	}

	@Override
	public QueryNode getQueryNode() {
		return this.mQueryNode;
	}

	@Override
	public void setQueryNode(QueryNode queryNode) {
		this.mQueryNode = queryNode;
	}

	@Override
	public boolean hasInlineCount() {
		return mHasInlineCount;
	}

	@Override
	public List<Pair<String, QueryOrder>> getOrderBy() {
		return mOrderBy;
	}

	@Override
	public List<String> getProjection() {
		return mProjection;
	}

	@Override
	public List<Pair<String, String>> getUserDefinedParameters() {
		return mUserDefinedParameters;
	}

	@Override
	public int getTop() {
		return mTop;
	}

	@Override
	public int getSkip() {
		return mSkip;
	}

	@Override
	public String getTableName() {
		return mTableName;
	}

	@Override
	public Query tableName(String tableName) {
		this.mTableName = tableName;
		return this;
	}

	/****** Row Operations ******/

	@Override
	public Query parameter(String parameter, String value) {
		this.mUserDefinedParameters.add(new Pair<String, String>(parameter, value));
		return this;
	}

	@Override
	public Query orderBy(String field, QueryOrder order) {
		this.mOrderBy.add(new Pair<String, QueryOrder>(field, order));
		return this;
	}

	@Override
	public Query top(int top) {
		if (top > 0) {
			this.mTop = top;
		}

		return this;
	}

	@Override
	public Query skip(int skip) {
		if (skip > 0) {
			this.mSkip = skip;
		}

		return this;
	}

	@Override
	public Query includeInlineCount() {
		this.mHasInlineCount = true;

		return this;
	}

	@Override
	public Query select(String... fields) {
		this.mProjection = new ArrayList<String>();
		for (String field : fields) {
			this.mProjection.add(field);
		}

		return this;
	}

	/****** Query Operations ******/

	@Override
	public Query field(String fieldName) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.field(fieldName));
		return this;
	}

	@Override
	public Query val(Number number) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.val(number));
		return this;
	}

	@Override
	public Query val(boolean val) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.val(val));
		return this;
	}

	@Override
	public Query val(String s) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.val(s));
		return this;
	}

	@Override
	public Query val(Date date) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.val(date));
		return this;
	}

	/****** Logical Operators ******/

	@Override
	public Query and() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.and());
		return this;
	}

	@Override
	public Query and(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.and(otherQuery));
		return this;
	}

	@Override
	public Query or() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.or());
		return this;
	}

	@Override
	public Query or(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.or(otherQuery));
		return this;
	}

	@Override
	public Query not() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.not());
		return this;
	}

	@Override
	public Query not(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.not(otherQuery));
		return this;
	}

	@Override
	public Query not(boolean booleanValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.not(QueryOperations.val(booleanValue)));
		return this;
	}

	/****** Comparison Operators ******/

	@Override
	public Query ge() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ge());
		return this;
	}

	@Override
	public Query ge(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ge(otherQuery));
		return this;
	}

	@Override
	public Query ge(Number numberValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ge(QueryOperations.val(numberValue)));
		return this;
	}

	@Override
	public Query ge(Date dateValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ge(QueryOperations.val(dateValue)));
		return this;
	}

	@Override
	public Query le() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.le());
		return this;
	}

	@Override
	public Query le(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.le(otherQuery));
		return this;
	}

	@Override
	public Query le(Number numberValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.le(QueryOperations.val(numberValue)));
		return this;
	}

	@Override
	public Query le(Date dateValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.le(QueryOperations.val(dateValue)));
		return this;
	}

	@Override
	public Query gt() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.gt());
		return this;
	}

	@Override
	public Query gt(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.gt(otherQuery));
		return this;
	}

	@Override
	public Query gt(Number numberValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.gt(QueryOperations.val(numberValue)));
		return this;
	}

	@Override
	public Query gt(Date dateValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.gt(QueryOperations.val(dateValue)));
		return this;
	}

	@Override
	public Query lt() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.lt());
		return this;
	}

	@Override
	public Query lt(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.lt(otherQuery));
		return this;
	}

	@Override
	public Query lt(Number numberValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.lt(QueryOperations.val(numberValue)));
		return this;
	}

	@Override
	public Query lt(Date dateValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.lt(QueryOperations.val(dateValue)));
		return this;
	}

	@Override
	public Query eq() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.eq());
		return this;
	}

	@Override
	public Query eq(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.eq(otherQuery));
		return this;
	}

	@Override
	public Query eq(Number numberValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.eq(QueryOperations.val(numberValue)));
		return this;
	}

	@Override
	public Query eq(boolean booleanValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.eq(QueryOperations.val(booleanValue)));
		return this;
	}

	@Override
	public Query eq(String stringValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.eq(QueryOperations.val(stringValue)));
		return this;
	}

	@Override
	public Query eq(Date dateValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.eq(QueryOperations.val(dateValue)));
		return this;
	}

	@Override
	public Query ne() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ne());
		return this;
	}

	@Override
	public Query ne(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ne(otherQuery));
		return this;
	}

	@Override
	public Query ne(Number numberValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ne(QueryOperations.val(numberValue)));
		return this;
	}

	@Override
	public Query ne(boolean booleanValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ne(QueryOperations.val(booleanValue)));
		return this;
	}

	@Override
	public Query ne(String stringValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ne(QueryOperations.val(stringValue)));
		return this;
	}

	@Override
	public Query ne(Date dateValue) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ne(QueryOperations.val(dateValue)));
		return this;
	}

	/****** Arithmetic Operators ******/

	@Override
	public Query add() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.add());
		return this;
	}

	@Override
	public Query add(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.add(otherQuery));
		return this;
	}

	@Override
	public Query add(Number val) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.add(val));
		return this;
	}

	@Override
	public Query sub() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.sub());
		return this;
	}

	@Override
	public Query sub(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.sub(otherQuery));
		return this;
	}

	@Override
	public Query sub(Number val) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.sub(val));
		return this;
	}

	@Override
	public Query mul() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.mul());
		return this;
	}

	@Override
	public Query mul(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.mul(otherQuery));
		return this;
	}

	@Override
	public Query mul(Number val) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.mul(val));
		return this;
	}

	@Override
	public Query div() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.div());
		return this;
	}

	@Override
	public Query div(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.div(otherQuery));
		return this;
	}

	@Override
	public Query div(Number val) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.div(val));
		return this;
	}

	@Override
	public Query mod() throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.mod());
		return this;
	}

	@Override
	public Query mod(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.mod(otherQuery));
		return this;
	}

	@Override
	public Query mod(Number val) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.mod(val));
		return this;
	}

	/****** Date Operators ******/

	@Override
	public Query year(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.year(otherQuery));
		return this;
	}

	@Override
	public Query year(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.year(field));
		return this;
	}

	@Override
	public Query month(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.month(otherQuery));
		return this;
	}

	@Override
	public Query month(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.month(field));
		return this;
	}

	@Override
	public Query day(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.day(otherQuery));
		return this;
	}

	@Override
	public Query day(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.day(field));
		return this;
	}

	@Override
	public Query hour(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.hour(otherQuery));
		return this;
	}

	@Override
	public Query hour(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.hour(field));
		return this;
	}

	@Override
	public Query minute(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.minute(otherQuery));
		return this;
	}

	@Override
	public Query minute(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.minute(field));
		return this;
	}

	@Override
	public Query second(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.second(otherQuery));
		return this;
	}

	@Override
	public Query second(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.second(field));
		return this;
	}

	/****** Math Functions ******/

	@Override
	public Query floor(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.floor(otherQuery));
		return this;
	}

	@Override
	public Query ceiling(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.ceiling(otherQuery));
		return this;
	}

	@Override
	public Query round(Query otherQuery) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.round(otherQuery));
		return this;
	}

	/****** String Operators ******/

	@Override
	public Query toLower(Query exp) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.toLower(exp));
		return this;
	}

	@Override
	public Query toLower(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.toLower(field));
		return this;
	}

	@Override
	public Query toUpper(Query exp) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.toUpper(exp));
		return this;
	}

	@Override
	public Query toUpper(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.toUpper(field));
		return this;
	}

	@Override
	public Query length(Query exp) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.length(exp));
		return this;
	}

	@Override
	public Query length(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.length(field));
		return this;
	}

	@Override
	public Query trim(Query exp) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.trim(exp));
		return this;
	}

	@Override
	public Query trim(String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.trim(field));
		return this;
	}

	@Override
	public Query startsWith(Query field, Query start) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.startsWith(field, start));
		return this;
	}

	@Override
	public Query startsWith(String field, String start) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.startsWith(field, start));
		return this;
	}

	@Override
	public Query endsWith(Query field, Query end) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.endsWith(field, end));
		return this;
	}

	@Override
	public Query endsWith(String field, String end) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.endsWith(field, end));
		return this;
	}

	@Override
	public Query subStringOf(Query str1, Query str2) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.subStringOf(str1, str2));
		return this;
	}

	@Override
	public Query subStringOf(String str, String field) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.subStringOf(str, field));
		return this;
	}

	@Override
	public Query concat(Query str1, Query str2) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.concat(str1, str2));
		return this;
	}

	@Override
	public Query indexOf(Query haystack, Query needle) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.indexOf(haystack, needle));
		return this;
	}

	@Override
	public Query indexOf(String field, String needle) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.indexOf(field, needle));
		return this;
	}

	@Override
	public Query subString(Query str, Query pos) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.subString(str, pos));
		return this;
	}

	@Override
	public Query subString(String field, int pos) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.subString(field, pos));
		return this;
	}

	@Override
	public Query subString(Query str, Query pos, Query length) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.subString(str, pos, length));
		return this;
	}

	@Override
	public Query subString(String field, int pos, int length) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.subString(field, pos, length));
		return this;
	}

	@Override
	public Query replace(Query str, Query find, Query replace) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.replace(str, find, replace));
		return this;
	}

	@Override
	public Query replace(String field, String find, String replace) throws MobileServiceException {
		QueryOperations.join(this, QueryOperations.replace(field, find, replace));
		return this;
	}
}