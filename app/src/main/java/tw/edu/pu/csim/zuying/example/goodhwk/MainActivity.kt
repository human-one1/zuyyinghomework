package tw.edu.pu.csim.zuying.example.goodhwk

import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() ,OnGestureListener,OnTouchListener,
    GestureDetector.OnDoubleTapListener, OnClickListener{

    lateinit var txv:TextView
    lateinit var gDetector: GestureDetector
    lateinit var old: Button
    lateinit var exe:Button
    lateinit var mper:MediaPlayer




    var count:Int = 0

    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var img3: ImageView
    lateinit var img4: ImageView
    lateinit var img5: ImageView
    lateinit var imgyear: ImageView
    lateinit var imgbadmi: ImageView
    lateinit var imgbyc: ImageView
    lateinit var imgmui: ImageView

    var size:Float = 30f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        imgbadmi = findViewById(R.id.imgbadmi)
        imgbyc = findViewById(R.id.imgbyc)
        imgmui = findViewById(R.id.imgmui)
        imgmui.setOnTouchListener(this)
        img1.setOnTouchListener(this)
        img2.setOnTouchListener(this)
        img3.setOnTouchListener(this)
        imgbadmi.setOnTouchListener(this)
        imgbyc.setOnTouchListener(this)

        old = findViewById(R.id.old)
        old.setOnClickListener(this)
        exe = findViewById(R.id.exe)
        exe.setOnClickListener(this)

        img4 = findViewById(R.id.img4)
        img4.visibility=View.GONE
        Glide.with(this)
            .load(R.drawable.happy)
            .circleCrop()
            .into(img4)
        mper = MediaPlayer()
        mper = MediaPlayer.create(this, R.raw.scenario)
        mper.start()


        img5 = findViewById(R.id.img5)
        img5.visibility=View.GONE

        imgyear = findViewById(R.id.imgyear)
        imgyear.visibility=View.GONE

        imgbyc = findViewById(R.id.imgbyc)
        imgbyc.visibility=View.GONE
        Glide.with(this)
            .load(R.drawable.imgbycicle)
            .into(imgbyc)

        imgbadmi = findViewById(R.id.imgbadmi)
        imgbadmi.visibility=View.GONE
        Glide.with(this)
            .load(R.drawable.badminton)
            .into(imgbadmi)

        imgmui = findViewById(R.id.imgmui)
        imgmui.visibility=View.GONE
        Glide.with(this)
            .load(R.drawable.music)
            .into(imgmui)


        txv = findViewById(R.id.txv)
        txv.setTextColor(Color.parseColor("#eeee00"))
        txv.setBackgroundColor(Color.BLUE)
        txv.setTypeface(
            Typeface.createFromAsset(assets,
                "font/HanyiSentyFingerPainting.ttf"))
        txv.getBackground().setAlpha(50)  //0~255透明度值
        gDetector=GestureDetector(this,this)

    }

    fun snake(v:View){
        val txv:TextView = findViewById<TextView>(R.id.txv)
        if (size<50){
            size++
            findViewById<TextView>(R.id.txv).setTextSize(size)
            txv.setTextSize(size)
            var cake:String
            cake="泡芙、芋圓、冰淇淋"
            txv.setText(cake)

        }

    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (txv.text == "手勢辨別"){
            txv.text = "靜宜之美"
        }
        else{
            txv.text = "手勢辨別"
        }
        gDetector.onTouchEvent(event)
        return true
    }



    override fun onDown(p0: MotionEvent): Boolean {
        txv.text = "按下"
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        txv.text = "按下後無後續動作"

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        txv.text = "短按"
        return true
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        if (distanceY >= 0){
            txv.text = "向上拖曳"
        }
        else{
            txv.text = "向下拖曳"
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        txv.text = "長按"


    }

    override fun onFling(e1: MotionEvent, e2:MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (e1.x <= e2.x){
            txv.text = "往右快滑"
            count++
            if(count>6){count=0}
        }
        else{
            txv.text = "往左快滑"
            count--
            if(count<0){count=6}
        }
        var res:Int = getResources().getIdentifier("pu" + count.toString(),
            "drawable", getPackageName())
        findViewById<ConstraintLayout>(R.id.bg).setBackgroundResource(res)
        return true
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v==img1){
            txv.text = "精靈1"
        }
        else if (v==img3){
            txv.text = "What's your name?"
        }
        else{
            txv.text = "精靈2"
        }
        if (event?.action == MotionEvent.ACTION_MOVE){
            v?.x = event.rawX- v!!.width/2
            v?.y = event.rawY- v!!.height/2
        }
        var r1: Rect = Rect(img1.x.toInt(), img1.y.toInt(),
            img1.x.toInt() + img1.width, img1.y.toInt() + img1.height)
        var r2: Rect = Rect(img2.x.toInt(), img2.y.toInt(),
            img2.x.toInt() + img2.width, img2.y.toInt() + img2.height)
        var r3: Rect = Rect(img3.x.toInt(), img3.y.toInt(),
            img2.x.toInt() + img3.width, img3.y.toInt() + img3.height)
        if(r1.intersect(r2)) {
            txv.text = "交個朋友吧!\uD83D\uDE09"
            img4.visibility=View.VISIBLE
        }
        if(r1.intersect(r3)){
            txv.text = "My name is 陳咨穎"
            img5.visibility=View.VISIBLE
        }
        if(r2.intersect(r3)){
            txv.text = "My name is 陳咨穎"
            img5.visibility=View.VISIBLE
        }


        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent): Boolean {
        txv.text="快按兩下"
        img4.visibility=View.GONE
        img5.visibility=View.GONE
        imgyear.visibility=View.GONE
        imgmui.visibility=View.GONE
        imgbadmi.visibility=View.GONE
        imgbyc.visibility=View.GONE
        img1.visibility=View.VISIBLE
        img2.visibility=View.VISIBLE
        img3.visibility=View.VISIBLE

        return true
    }

    override fun onDoubleTap(p0: MotionEvent): Boolean {
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent): Boolean {
        return true
    }

    override fun onClick(v: View?) {
        if (v==old){
            imgyear.visibility=View.VISIBLE
            img1.visibility=View.GONE
            img2.visibility=View.GONE
            img3.visibility=View.GONE
        }
        if (v==exe){
            imgmui.visibility=View.VISIBLE
            imgbadmi.visibility=View.VISIBLE
            imgbyc.visibility=View.VISIBLE
            img1.visibility=View.GONE
            img2.visibility=View.GONE
            img3.visibility=View.GONE
        }



    }
}