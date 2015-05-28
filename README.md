# SimpleTagImageView
ImageView with a tag in android. So it's a ImageView.

## Demo

   <img src="https://raw.githubusercontent.com/wujingchao/SimpleTagImageView/master/demo2.gif"  width="401" heigit="638" alt="Screenshot"/>


####Warning:When you set the round radius,the simpletagimageview scale type just  support fitXY now!
<br/>
<p>
   <img src="https://raw.githubusercontent.com/wujingchao/SimpleTagImageView/master/demo.jpg"  width="540" heigit="960" alt="Screenshot"/>
</p>

<br/>

##Usage

    <net.wujingchao.android.view.SimpleTagImageView
            xmlns:simple="http://schemas.android.com/apk/res-auto"
            android:id="@+id/stiv"
            android:layout_margin="18dip"
            android:layout_width="256dip"
            android:layout_height="192dip"
            android:src="@drawable/qwer"
            simple:simple_tag_textSize="18sp"
            simple:simple_corner_distance="30dp"
            simple:simple_tag_text="AB"
            simple:simple_tag_background_color="#af27CDC0"
            simple:simple_tag_orientation="left_bottom"
            simple:simple_tag_width="20dip"
            simple:simple_tag_textColor="@android:color/white"
            simple:simple_tag_enable="true"
            simple:simple_tag_round_radius="20dp"/>
	
### Quick Start

####Gradle

	compile 'net.wujingchao.android.view:simple-tag-imageview:1.0.1'

####Maven
	
	<!-- you should configure jcenter repository-->
	<dependency>
		<groupId>net.wujingchao.android.view</groupId>
		<artifactId>simple-tag-imageview</artifactId>
		<version>1.0.1</version>
		<type>aar</type>
	</dependency> 


##Developed By


- wujingchao -  wujingchao@aliyun.com




License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
