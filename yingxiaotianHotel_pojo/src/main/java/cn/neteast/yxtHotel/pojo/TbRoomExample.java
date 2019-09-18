package cn.neteast.yxtHotel.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbRoomExample() {
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

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(Long value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(Long value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(Long value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(Long value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(Long value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(Long value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<Long> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<Long> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(Long value1, Long value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(Long value1, Long value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomNumberIsNull() {
            addCriterion("room_number is null");
            return (Criteria) this;
        }

        public Criteria andRoomNumberIsNotNull() {
            addCriterion("room_number is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNumberEqualTo(String value) {
            addCriterion("room_number =", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotEqualTo(String value) {
            addCriterion("room_number <>", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberGreaterThan(String value) {
            addCriterion("room_number >", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberGreaterThanOrEqualTo(String value) {
            addCriterion("room_number >=", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberLessThan(String value) {
            addCriterion("room_number <", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberLessThanOrEqualTo(String value) {
            addCriterion("room_number <=", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberLike(String value) {
            addCriterion("room_number like", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotLike(String value) {
            addCriterion("room_number not like", value, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberIn(List<String> values) {
            addCriterion("room_number in", values, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotIn(List<String> values) {
            addCriterion("room_number not in", values, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberBetween(String value1, String value2) {
            addCriterion("room_number between", value1, value2, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomNumberNotBetween(String value1, String value2) {
            addCriterion("room_number not between", value1, value2, "roomNumber");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdIsNull() {
            addCriterion("room_type_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdIsNotNull() {
            addCriterion("room_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdEqualTo(Long value) {
            addCriterion("room_type_id =", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdNotEqualTo(Long value) {
            addCriterion("room_type_id <>", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdGreaterThan(Long value) {
            addCriterion("room_type_id >", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("room_type_id >=", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdLessThan(Long value) {
            addCriterion("room_type_id <", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("room_type_id <=", value, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdIn(List<Long> values) {
            addCriterion("room_type_id in", values, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdNotIn(List<Long> values) {
            addCriterion("room_type_id not in", values, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdBetween(Long value1, Long value2) {
            addCriterion("room_type_id between", value1, value2, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("room_type_id not between", value1, value2, "roomTypeId");
            return (Criteria) this;
        }

        public Criteria andRoomDescIsNull() {
            addCriterion("room_desc is null");
            return (Criteria) this;
        }

        public Criteria andRoomDescIsNotNull() {
            addCriterion("room_desc is not null");
            return (Criteria) this;
        }

        public Criteria andRoomDescEqualTo(String value) {
            addCriterion("room_desc =", value, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescNotEqualTo(String value) {
            addCriterion("room_desc <>", value, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescGreaterThan(String value) {
            addCriterion("room_desc >", value, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescGreaterThanOrEqualTo(String value) {
            addCriterion("room_desc >=", value, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescLessThan(String value) {
            addCriterion("room_desc <", value, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescLessThanOrEqualTo(String value) {
            addCriterion("room_desc <=", value, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescLike(String value) {
            addCriterion("room_desc like", value, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescNotLike(String value) {
            addCriterion("room_desc not like", value, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescIn(List<String> values) {
            addCriterion("room_desc in", values, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescNotIn(List<String> values) {
            addCriterion("room_desc not in", values, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescBetween(String value1, String value2) {
            addCriterion("room_desc between", value1, value2, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomDescNotBetween(String value1, String value2) {
            addCriterion("room_desc not between", value1, value2, "roomDesc");
            return (Criteria) this;
        }

        public Criteria andRoomPicIsNull() {
            addCriterion("room_pic is null");
            return (Criteria) this;
        }

        public Criteria andRoomPicIsNotNull() {
            addCriterion("room_pic is not null");
            return (Criteria) this;
        }

        public Criteria andRoomPicEqualTo(String value) {
            addCriterion("room_pic =", value, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicNotEqualTo(String value) {
            addCriterion("room_pic <>", value, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicGreaterThan(String value) {
            addCriterion("room_pic >", value, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicGreaterThanOrEqualTo(String value) {
            addCriterion("room_pic >=", value, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicLessThan(String value) {
            addCriterion("room_pic <", value, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicLessThanOrEqualTo(String value) {
            addCriterion("room_pic <=", value, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicLike(String value) {
            addCriterion("room_pic like", value, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicNotLike(String value) {
            addCriterion("room_pic not like", value, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicIn(List<String> values) {
            addCriterion("room_pic in", values, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicNotIn(List<String> values) {
            addCriterion("room_pic not in", values, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicBetween(String value1, String value2) {
            addCriterion("room_pic between", value1, value2, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPicNotBetween(String value1, String value2) {
            addCriterion("room_pic not between", value1, value2, "roomPic");
            return (Criteria) this;
        }

        public Criteria andRoomPriceIsNull() {
            addCriterion("room_price is null");
            return (Criteria) this;
        }

        public Criteria andRoomPriceIsNotNull() {
            addCriterion("room_price is not null");
            return (Criteria) this;
        }

        public Criteria andRoomPriceEqualTo(Integer value) {
            addCriterion("room_price =", value, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceNotEqualTo(Integer value) {
            addCriterion("room_price <>", value, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceGreaterThan(Integer value) {
            addCriterion("room_price >", value, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_price >=", value, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceLessThan(Integer value) {
            addCriterion("room_price <", value, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceLessThanOrEqualTo(Integer value) {
            addCriterion("room_price <=", value, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceIn(List<Integer> values) {
            addCriterion("room_price in", values, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceNotIn(List<Integer> values) {
            addCriterion("room_price not in", values, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceBetween(Integer value1, Integer value2) {
            addCriterion("room_price between", value1, value2, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("room_price not between", value1, value2, "roomPrice");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableIsNull() {
            addCriterion("room_available is null");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableIsNotNull() {
            addCriterion("room_available is not null");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableEqualTo(Long value) {
            addCriterion("room_available =", value, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableNotEqualTo(Long value) {
            addCriterion("room_available <>", value, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableGreaterThan(Long value) {
            addCriterion("room_available >", value, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableGreaterThanOrEqualTo(Long value) {
            addCriterion("room_available >=", value, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableLessThan(Long value) {
            addCriterion("room_available <", value, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableLessThanOrEqualTo(Long value) {
            addCriterion("room_available <=", value, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableIn(List<Long> values) {
            addCriterion("room_available in", values, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableNotIn(List<Long> values) {
            addCriterion("room_available not in", values, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableBetween(Long value1, Long value2) {
            addCriterion("room_available between", value1, value2, "roomAvailable");
            return (Criteria) this;
        }

        public Criteria andRoomAvailableNotBetween(Long value1, Long value2) {
            addCriterion("room_available not between", value1, value2, "roomAvailable");
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