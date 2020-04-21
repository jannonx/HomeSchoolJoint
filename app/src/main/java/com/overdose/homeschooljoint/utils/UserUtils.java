package com.overdose.homeschooljoint.utils;

import com.google.gson.Gson;
import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.bean.UserDataBean;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/4/21 17:29
 */
public class UserUtils {

  private static UserUtils userUtils;
  private UserDataBean mUserBean;

  private UserUtils() {
  }

  public static UserUtils getInstance() {
    if (userUtils == null) {
      synchronized (UserUtils.class) {
        if (userUtils == null) {
          userUtils = new UserUtils();
        }
      }
    }
    return userUtils;
  }

  public UserDataBean getUserData() {
    if (mUserBean == null) {
      String userStr =
          (String) AppApplication.getInstance().getCacheData(ConstantValue.USER_JSON_STRING, "");
      mUserBean = new Gson().fromJson(userStr, UserDataBean.class);
    }
    return mUserBean;
  }


}
