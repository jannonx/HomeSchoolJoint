package com.overdose.homeschooljoint.utils;

import com.overdose.homeschooljoint.BuildConfig;
import com.overdose.homeschooljoint.bean.CourseBean;
import com.overdose.homeschooljoint.bean.SimpleRBean;
import com.overdose.homeschooljoint.bean.TeacherListBean;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.bean.ClassBean;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetApi {

  String API_NODE_SERVER_URL = BuildConfig.API_SERVER_URL;

  /**
   * 登入
   */
  @GET("student/login")
  Observable<UserBean> requestLogin(@Query("code") String code,
                                    @Query("password") String password,
                                    @Query("role") String role);

  /**
   * 注册
   */
  @GET("{role_name}/registere")
  Observable<UserBean> queryRegister(@Path("role_name") String roleName,
                                     @Query("name") String name,
                                     @Query("code") String code,
                                     @Query("password") String password,
                                     @Query("classs") String classs,
                                     @Query("sex") String sex,
                                     @Query("coursecode") String courseCode,
                                     @Query("role") String role);

  /**
   * 修改密码（老师）
   */
  @GET("teacher/teacher-edl")
  Observable<SimpleRBean> modifyPwByTeacher(@Query("teacherCode") String teacherCode,
                                            @Query("password") String password);

  /**
   * 修改密码(学生)
   */
  @GET("student/student-edl")
  Observable<SimpleRBean> modifyPwByStudent(@Query("studentCode") String studentCode,
                                            @Query("password") String password);

  /**
   * 获取老师列表
   */
  @GET("student/select-doctor-classcode")
  Observable<TeacherListBean> getTeacherList(@Query("classcode") String classcode);

  /**
   * 学生请假
   */
  @POST("student/add-leave")
  Observable<SimpleRBean> leaveByStudent(@Body RequestBody params);

  /**
   * 学生请假列表
   */
  @POST("student/select-leave")
  Observable<SimpleRBean> getLeaveListByStudent(@Query("studentCode") String studentCode);

  ///=====================================================teacher==========================

  /**
   * 获取班级列表
   */
  @GET("teacher/select-class")
  Observable<ClassBean> getClasses();

  /**
   * 获取班级列表
   */
  @GET("teacher/select-course")
  Observable<CourseBean> getCourse();


}
