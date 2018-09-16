package com.zhqz.wisc.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by guoxuezhu on 16-11-15.
 */
@Entity
public class CourseTwo {
    /**
     * 课程名称
     */
    public int courseId;

    /**
     * 课程名称
     */
    public String name;

    /**
     * 课程id
     */
    public int activityCourseId;
    /**
     * 课时id
     */
    public int periodId;
    /**
     * 教师名称
     */
    public String teacherName;
    /**
     * 教师id
     */
    public int teacherId;
    /**
     * 开始时间
     */
    public String startTime;
    /**
     * 结束时间
     */
    public String endTime;

    /**
     * 课程简介
     */
    public String courseforshort;
    public String lessonNum;

    @Generated(hash = 617390581)
    public CourseTwo(int courseId, String name, int activityCourseId, int periodId,
                     String teacherName, int teacherId, String startTime, String endTime,
                     String courseforshort, String lessonNum) {
        this.courseId = courseId;
        this.name = name;
        this.activityCourseId = activityCourseId;
        this.periodId = periodId;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseforshort = courseforshort;
        this.lessonNum = lessonNum;
    }

    @Generated(hash = 991936574)
    public CourseTwo() {
    }

    @Override
    public String toString() {
        return "CourseTwo{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", activityCourseId=" + activityCourseId +
                ", periodId=" + periodId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherId=" + teacherId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", courseforshort='" + courseforshort + '\'' +
                ", lessonNum='" + lessonNum + '\'' +
                '}';
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActivityCourseId() {
        return this.activityCourseId;
    }

    public void setActivityCourseId(int activityCourseId) {
        this.activityCourseId = activityCourseId;
    }

    public int getPeriodId() {
        return this.periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCourseforshort() {
        return this.courseforshort;
    }

    public void setCourseforshort(String courseforshort) {
        this.courseforshort = courseforshort;
    }

    public String getLessonNum() {
        return this.lessonNum;
    }

    public void setLessonNum(String lessonNum) {
        this.lessonNum = lessonNum;
    }
}