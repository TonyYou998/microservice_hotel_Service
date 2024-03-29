package com.uit.microservice_hotel_service.common;


import common.BaseConstant;

public class HostConstant extends BaseConstant {

    public static final String demo="/demo";
    public static final String create_a_room="/create-a-room";

    public static final String create_a_property="/create-a-property";
    public static final String ERROR="error";
    public static final String delete_a_room="/delete-a-room/{id}";

    public static final String get_all_room="/get-all-room";

    public static final String get_a_room="/get-a-room/{id}";

    public static final String edit_a_room="/edit-a-room";
    public static final String BECOME_A_HOST="/become-a-host";

    public static final String SERVICE_NAME ="host" ;
    public static final String ADD_PROPERTY = "/add-property";

    public static final String GET_PROPERTY_TYPE ="/get-property-type" ;
    public static final String GET_PROPERTY_BY_HOST_ID = "/get-property-by-host-id";
    public static final String GET_RECENT_PROPERTY = "/get-recent";
    public static final String GET_PROPERTY_BY_ID = "/get-property-by-id/{propertyId}";
    public static final String GET_HOST_ID_BY_PROPERTY_ID = "/get-host-by-property-id/{propertyId}";
}
