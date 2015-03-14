# SimpleTagImageView
ImageView with a tag in android. So it's a ImageView.

# Demo
<p>
   <img src="./demo1.png" width="226" alt="Screenshot"/>
</p>

<br/>
<br/>

<img src="./demo3.gif" width="320" alt="Screenshot"/>



    <net.wujingchao.android.view.SimpleTagImageView
            xmlns:simple="http://schemas.android.com/apk/res-auto"
            android:id="@+id/stiv"
            android:layout_width="150dip"
            android:layout_height="150dip"
            android:src="@drawable/qq"
            simple:simple_tag_textSize="18sp"
            simple:simple_corner_distance="30dp"
            simple:simple_tag_text="AB"
            simple:simple_tag_background_color="#af27CDC0"
            simple:simple_tag_orientation="left_bottom"
            simple:simple_tag_width="20dip"
            simple:simple_tag_textColor="@android:color/white"/> 
	
#### In order to use it , just import as a module in android studio. In gradle dependencies append :
	compile project(':library')  //library is your module name

######You can mail me => wujingchao@aliyun.com 




