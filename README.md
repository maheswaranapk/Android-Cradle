# Android-Cradle
This is a Simple Android Implementation of Newton Cradle.

**Design Inspired from [
Cradle - Material Up!](http://www.materialup.com/posts/material-cradle)**

# **Features**
+ Change Number of Spheres.
+ Change Number of Moving Spheres.

# **How To use**
Copy **CircleIndicator.java** to the project.

In Layout xml,
```xml
             <com.scriptedpapers.CradleLoader
                    android:id="@+id/cradleLoader"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent" />
```

In View,
```
            cradleLoader.startAnimation(ballCount, movingBallCount);
```

# Task

- [x] Basic Implementation.
- [x] Change Number of Spheres.
- [x] Change Number of Moving Spheres.
- [ ] Sphere Animation.

# **Output**
![alt tag](https://github.com/maheswaranapk/Android-Cradle/blob/master/sample/materialup_sample.gif)
