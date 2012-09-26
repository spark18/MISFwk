package com.shizhong.db.updater;

import static com.shizhong.web.bizmodel.EntityType.BusTicketAirPreview;
import static com.shizhong.web.bizmodel.EntityType.BusTicketAirView;
import static com.shizhong.web.bizmodel.EntityType.BusTicketPreview;
import static com.shizhong.web.bizmodel.EntityType.BusTicketView;
import static com.shizhong.web.bizmodel.EntityType.DownLinePreview;
import static com.shizhong.web.bizmodel.EntityType.DownLineView;
import static com.shizhong.web.bizmodel.EntityType.LiGan;
import static com.shizhong.web.bizmodel.EntityType.LiGanPreview;
import static com.shizhong.web.bizmodel.EntityType.SZTransaction;
import static com.shizhong.web.bizmodel.EntityType.TingZi;
import static com.shizhong.web.bizmodel.EntityType.TingZiNanHui;
import static com.shizhong.web.bizmodel.EntityType.TingZiNanHuiPreview;
import static com.shizhong.web.bizmodel.EntityType.TingZiPreview;
import static com.shizhong.web.bizmodel.EntityType.UpLinePreview;
import static com.shizhong.web.bizmodel.EntityType.UpLineView;
import static com.shizhong.web.bizmodel.EntityType.YangZhaoDian;
import static com.shizhong.web.bizmodel.EntityType.YangZhaoDianPreview;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.shizhong.web.bizmodel.EntityType;
import com.shizhong.web.meta.Parameter;

public class UpdaterRepo {
    private final Map<EntityType, Object> updaters = new HashMap<EntityType, Object>();

    private static final UpdaterRepo instance = new UpdaterRepo();
    {
        updaters.put(LiGanPreview, new LiGanPreviewUpdater());
        updaters.put(LiGan, new LiGanUpdater());
        updaters.put(SZTransaction, new LiGanUpdater());
        updaters.put(UpLinePreview, new UpLinePreviewUpdater());
        updaters.put(UpLineView, new UpLineViewUpdater());
        updaters.put(DownLinePreview, new DownLinePreviewUpdater());
        updaters.put(DownLineView, new DownLineViewUpdater());
        updaters.put(BusTicketAirPreview, new BusTicketAirPreviewUpdater());
        updaters.put(BusTicketAirView, new BusTicketAirViewUpdater());
        updaters.put(BusTicketPreview, new BusTicketPreviewUpdater());
        updaters.put(BusTicketView, new BusTicketViewUpdater());
        updaters.put(TingZiPreview, new TingZiPreviewUpdater());
        updaters.put(TingZi, new TingZiUpdater());
        updaters.put(TingZiNanHuiPreview, new TingZiNanHuiPreviewUpdater());
        updaters.put(TingZiNanHui, new TingZiNanHuiUpdater());
        updaters.put(YangZhaoDianPreview, new YangZhaoDianPreviewUpdater());
        updaters.put(YangZhaoDian, new YangZhaoDianUpdater());

    }

    public static final UpdaterRepo getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> Updater<T> getUpdater(EntityType name) {
        return (Updater<T>) updaters.get(name);
    }

    private static final SimpleDateFormat formatter_zh_CN = new SimpleDateFormat("MMM dd,yyyy", Locale.CHINA);

    public static Date recordDateModified(Object dataObj, Parameter<String> param, Map<String, String[]> modifiedFields) {
        String fieldName = param.getName();
        String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        Date oldValue = null;
        Method getter = null;
        try {
            getter = dataObj.getClass().getDeclaredMethod(getterName, (Class<?>[]) null);
            Object value = getter.invoke(dataObj, (Object[]) null);
            if (value != null) {
                oldValue = (Date) value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date newDigdate = null;
        try {
            newDigdate = formatter_zh_CN.parse(param.getValue());
            if (null != oldValue && newDigdate != null && newDigdate.compareTo(oldValue) != 0 || null == oldValue
                    && oldValue != newDigdate || null == newDigdate && oldValue != newDigdate) {
                modifiedFields.put(param.getName(), new String[] { newDigdate == null ? "" : newDigdate.toString(),
                        oldValue == null ? "" : oldValue.toString(), });
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDigdate;
    }

    public static String recordMofied(Object dataObj, Parameter<String> param, Map<String, String[]> modifiedFields) {
        String fieldName = param.getName();
        String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        String oldValue = "";
        Method getter = null;
        try {
            getter = dataObj.getClass().getDeclaredMethod(getterName, (Class<?>[]) null);
            Object value = getter.invoke(dataObj, (Object[]) null);
            if (value != null) {
                oldValue = value.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String newValue = param.getValue();
        if (null == newValue) {
            newValue = "";
        }
        if (null == oldValue) {
            oldValue = "";
        }
        Class<?> valueType = getter.getReturnType();
        if (valueType == Double.class) {
            if (Double.parseDouble(newValue) != Double.parseDouble(oldValue)) {
                modifiedFields.put(fieldName, new String[] { newValue, oldValue });
            }
        } else if (valueType == Long.class) {
            if (Long.parseLong(newValue) != Long.parseLong(oldValue)) {
                modifiedFields.put(fieldName, new String[] { newValue, oldValue });
            }
        } else {
            if (!newValue.equals(oldValue)) {
                modifiedFields.put(fieldName, new String[] { newValue, oldValue });
            }
        }
        return newValue;
    }

}
