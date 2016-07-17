// ISalary.aidl
package com.csj.aidl;

// Declare any non-default types here with import statements
//自定义的数据类型,需要import
import com.csj.aidl.Salary;
import com.csj.aidl.Person;

interface ISalary {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    //定义一个Person对象作为传入参数
    //接口中定义方法时,需要制定新参的传递模式,这里是传入,所以前面有一个in
    Salary getMsg(in Person owner);
}
