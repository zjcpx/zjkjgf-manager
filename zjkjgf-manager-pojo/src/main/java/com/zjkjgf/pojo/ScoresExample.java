package com.zjkjgf.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScoresExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScoresExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVottingpersonIsNull() {
            addCriterion("vottingPerson is null");
            return (Criteria) this;
        }

        public Criteria andVottingpersonIsNotNull() {
            addCriterion("vottingPerson is not null");
            return (Criteria) this;
        }

        public Criteria andVottingpersonEqualTo(String value) {
            addCriterion("vottingPerson =", value, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonNotEqualTo(String value) {
            addCriterion("vottingPerson <>", value, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonGreaterThan(String value) {
            addCriterion("vottingPerson >", value, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonGreaterThanOrEqualTo(String value) {
            addCriterion("vottingPerson >=", value, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonLessThan(String value) {
            addCriterion("vottingPerson <", value, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonLessThanOrEqualTo(String value) {
            addCriterion("vottingPerson <=", value, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonLike(String value) {
            addCriterion("vottingPerson like", value, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonNotLike(String value) {
            addCriterion("vottingPerson not like", value, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonIn(List<String> values) {
            addCriterion("vottingPerson in", values, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonNotIn(List<String> values) {
            addCriterion("vottingPerson not in", values, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonBetween(String value1, String value2) {
            addCriterion("vottingPerson between", value1, value2, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andVottingpersonNotBetween(String value1, String value2) {
            addCriterion("vottingPerson not between", value1, value2, "vottingperson");
            return (Criteria) this;
        }

        public Criteria andMatchprojectIsNull() {
            addCriterion("matchProject is null");
            return (Criteria) this;
        }

        public Criteria andMatchprojectIsNotNull() {
            addCriterion("matchProject is not null");
            return (Criteria) this;
        }

        public Criteria andMatchprojectEqualTo(String value) {
            addCriterion("matchProject =", value, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectNotEqualTo(String value) {
            addCriterion("matchProject <>", value, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectGreaterThan(String value) {
            addCriterion("matchProject >", value, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectGreaterThanOrEqualTo(String value) {
            addCriterion("matchProject >=", value, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectLessThan(String value) {
            addCriterion("matchProject <", value, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectLessThanOrEqualTo(String value) {
            addCriterion("matchProject <=", value, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectLike(String value) {
            addCriterion("matchProject like", value, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectNotLike(String value) {
            addCriterion("matchProject not like", value, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectIn(List<String> values) {
            addCriterion("matchProject in", values, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectNotIn(List<String> values) {
            addCriterion("matchProject not in", values, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectBetween(String value1, String value2) {
            addCriterion("matchProject between", value1, value2, "matchproject");
            return (Criteria) this;
        }

        public Criteria andMatchprojectNotBetween(String value1, String value2) {
            addCriterion("matchProject not between", value1, value2, "matchproject");
            return (Criteria) this;
        }

        public Criteria andHotscoresIsNull() {
            addCriterion("hotScores is null");
            return (Criteria) this;
        }

        public Criteria andHotscoresIsNotNull() {
            addCriterion("hotScores is not null");
            return (Criteria) this;
        }

        public Criteria andHotscoresEqualTo(Integer value) {
            addCriterion("hotScores =", value, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresNotEqualTo(Integer value) {
            addCriterion("hotScores <>", value, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresGreaterThan(Integer value) {
            addCriterion("hotScores >", value, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresGreaterThanOrEqualTo(Integer value) {
            addCriterion("hotScores >=", value, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresLessThan(Integer value) {
            addCriterion("hotScores <", value, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresLessThanOrEqualTo(Integer value) {
            addCriterion("hotScores <=", value, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresIn(List<Integer> values) {
            addCriterion("hotScores in", values, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresNotIn(List<Integer> values) {
            addCriterion("hotScores not in", values, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresBetween(Integer value1, Integer value2) {
            addCriterion("hotScores between", value1, value2, "hotscores");
            return (Criteria) this;
        }

        public Criteria andHotscoresNotBetween(Integer value1, Integer value2) {
            addCriterion("hotScores not between", value1, value2, "hotscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresIsNull() {
            addCriterion("specialistScores is null");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresIsNotNull() {
            addCriterion("specialistScores is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresEqualTo(Integer value) {
            addCriterion("specialistScores =", value, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresNotEqualTo(Integer value) {
            addCriterion("specialistScores <>", value, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresGreaterThan(Integer value) {
            addCriterion("specialistScores >", value, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresGreaterThanOrEqualTo(Integer value) {
            addCriterion("specialistScores >=", value, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresLessThan(Integer value) {
            addCriterion("specialistScores <", value, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresLessThanOrEqualTo(Integer value) {
            addCriterion("specialistScores <=", value, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresIn(List<Integer> values) {
            addCriterion("specialistScores in", values, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresNotIn(List<Integer> values) {
            addCriterion("specialistScores not in", values, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresBetween(Integer value1, Integer value2) {
            addCriterion("specialistScores between", value1, value2, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andSpecialistscoresNotBetween(Integer value1, Integer value2) {
            addCriterion("specialistScores not between", value1, value2, "specialistscores");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNull() {
            addCriterion("modifyTime is null");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNotNull() {
            addCriterion("modifyTime is not null");
            return (Criteria) this;
        }

        public Criteria andModifytimeEqualTo(Date value) {
            addCriterion("modifyTime =", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotEqualTo(Date value) {
            addCriterion("modifyTime <>", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThan(Date value) {
            addCriterion("modifyTime >", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modifyTime >=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThan(Date value) {
            addCriterion("modifyTime <", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThanOrEqualTo(Date value) {
            addCriterion("modifyTime <=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeIn(List<Date> values) {
            addCriterion("modifyTime in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotIn(List<Date> values) {
            addCriterion("modifyTime not in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeBetween(Date value1, Date value2) {
            addCriterion("modifyTime between", value1, value2, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotBetween(Date value1, Date value2) {
            addCriterion("modifyTime not between", value1, value2, "modifytime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}