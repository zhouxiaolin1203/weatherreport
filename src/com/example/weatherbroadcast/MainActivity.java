package com.example.weatherbroadcast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView textview_weather;
	private TextView textview_city;
	private TextView textview_wet;
	private TextView textview_wind;
	private TextView textview_temperature;
	private ImageView weathericon;
	private adapter madapter;
	private ListView mlistview;
	private String city;
	private String weather;
	private String wet;
	private String wind;
	private String week;
	private String temp;
	private List<weatherinfo> info=new ArrayList<weatherinfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ���ؼ�
		init();
		
		//��������xml
		try {
			weatherparse(this,"1");
			iconset(weathericon, weather);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		//�½�һ��������������ǰ�����Ĵ��ݹ�ȥ
		madapter=new adapter(MainActivity.this);
		mlistview.setAdapter(madapter);
	}

	//��ʼ���ؼ�
	private void init() {
		mlistview=(ListView) findViewById(R.id.weather_list);
		textview_weather=(TextView) findViewById(R.id.textview_weather);
		textview_temperature=(TextView) findViewById(R.id.textview_tempratrue);
		textview_wet=(TextView) findViewById(R.id.textview_wet);
		textview_wind=(TextView) findViewById(R.id.textview_wind);
		textview_city=(TextView) findViewById(R.id.textview_place);
		weathericon=(ImageView) findViewById(R.id.weather_icon);
	}
	
	//Ϊlistview���õ���¼�������Ϣ��ʾ���ֻ���Ļ�ϰ벿��
	public void itemclick(){
		mlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				weatherinfo weather=info.get(position);
				textview_city.setText(weather.getCity());
				textview_temperature.setText(weather.getTemperature());
				textview_weather.setText(weather.getWeather());
				textview_wet.setText("ʪ�ȣ�"+weather.getWet());
				textview_wind.setText("������"+weather.getWind());
				iconset(weathericon, weather.getWeather());
			}
		});
		
	}

	//��������xml
	private void weatherparse(Context context,String id) throws IOException, XmlPullParserException {

		
		//��ȡassetsĿ¼���������paraser
		AssetManager paraser = context.getAssets();
		//��assetsĿ¼�µ�����xml�ļ�
		
		InputStream input=paraser.open("weather.xml");
		
		//����һ��pull������
		XmlPullParser mparser= Xml.newPullParser();
		
		//��ʼ����������Ϊ������һ�����������������ͱ��뷽ʽ
		mparser.setInput(input, "utf-8");
		//��ȡ����������
		int type=mparser.getEventType();
		Log.i("type",type+"");
		while(type!=XmlPullParser.END_DOCUMENT){
			if(type==XmlPullParser.START_TAG){
				if("city".equals(mparser.getName()))
				{
					city=mparser.getAttributeValue(0);
					Log.i("city",city+"");
					
				}
				if("week".equals(mparser.getName())){
   					    if(id.equals(mparser.getAttributeValue(0))){
					    mparser.nextTag();	
						if("name".equals(mparser.getName())){
							weather=mparser.nextText();
							 mparser.nextTag();
						}
						if("temp".equals(mparser.getName())){
							temp=mparser.nextText();
							 mparser.nextTag();
						}
						if("wet".equals(mparser.getName())){
							wet=mparser.nextText();
							 mparser.nextTag();
						}
						if("wind".equals(mparser.getName())){
							wind=mparser.nextText();
						}
					}
					
				}
				
				
			}
			
			
			type=mparser.next();	
		}

	}
	//����һ��viewholder

	class viewholder{
		TextView week;
		TextView weather;
		TextView temperature;
		ImageView icon;

	}


	//�Զ���adapter

	class adapter extends BaseAdapter{
		Context context;
		LayoutInflater inflater;
		public adapter(Context context){
			this.context=context;
			this.inflater=LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			return 7;
		}
		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {

			viewholder mholder;
			if(view==null){
				mholder=new viewholder();
				view =inflater.inflate(R.layout.list_item, null);
				mholder.icon=(ImageView) view.findViewById(R.id.item_icon);
				mholder.weather=(TextView) view.findViewById(R.id.item_textview_weather);
				mholder.temperature=(TextView) view.findViewById(R.id.item_textview_temp);
				mholder.week=(TextView) view.findViewById(R.id.item_textview_week);
				view.setTag(mholder);
			}
			else{
				mholder=(viewholder) view.getTag();
			}
			//��������xml
			try {
				weatherparse(context,String.valueOf(position));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			//������صĲ���
			mholder.weather.setText(weather);
			mholder.temperature.setText(temp);
			mholder.week.setText(weekset(position+1));
			//��������ͼ��
			iconset(mholder.icon,weather);
			info.add(new weatherinfo(city,weather,temp,wet,wind));
			itemclick();
			//Toast.makeText(context, weekset(position)+"������"+weather+"�¶ȣ�"+temp+"ʪ��"+wet, 0).show();
			return view;
		}

	}
	//������������չʾ��ͼƬ
	public void iconset(ImageView weather_icon,String weather){
		if(weather.equals("����")){
			weather_icon.setBackgroundResource(R.drawable.sun);
		}
		else if(weather.equals("С��")){
			weather_icon.setBackgroundResource(R.drawable.lightrain);
		}
		else if(weather.equals("�������")){
			weather_icon.setBackgroundResource(R.drawable.weather_mostlycloudy);
		}
		else if(weather.equals("����")){
			weather_icon.setBackgroundResource(R.drawable.cloudy);
		}
		else if(weather.equals("�׵�����")){
			weather_icon.setBackgroundResource(R.drawable.storm);
		}
		else if(weather.equals("Сѩ")){
			weather_icon.setBackgroundResource(R.drawable.weather_snow);
		}


	}
	//����itemλ���������ڼ�
	public String  weekset(int position){
		switch (position) {
		case 1:
			return "����һ";
		case 2:
			return "���ڶ�";
		case 3:
			return "������";
		case 4:
			return "������";
		case 5:
			return "������";
		case 6:
			return "������";
		case 7:
			return "������";
		default:
			return "����һ";
		}
		
	}




}
