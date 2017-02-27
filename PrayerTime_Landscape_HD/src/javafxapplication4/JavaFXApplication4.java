/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
sudo cp  SimpleAstronomyLib-0.1.0.jar  /opt/jdk1.8.0/jre/lib/ext
sudo service samba restart
scp -P 2221 JavaFXApplication4.jar  pi@192.168.1.1:/home/pi/prayertime
or

rsync -v --progress -e ssh ossama@192.168.0.30:~/NetBeansProjects/PrayerTime_Landscape_HD/dist/JavaFXApplication4.jar /home/pi/prayertime/
rsync -r -v --progress -e ssh JavaFXApplication4.jar pi@192.168.0.41:/home/pi/prayertime/
sshpass -p "raspberry" scp -P 22 JavaFXApplication4.jar  pi@192.168.0.41:/home/pi/prayertime

change the following files to customise:
sudo nano /etc/ddclient.conf
sudo nano /etc/ppp/peers/pptpconf
sudo nano /etc/hosts
sudo nano /etc/hostname

26/11/13 from windows XP: Uncomment Lines 227, 214 - 221, 891 - 892 to work on raspberry pi

*/

//TODO change vpn setting to dns instead of ip, and setup my home to ossama.org for example
//TODO setup internet_able same as implemented in paramatta.

package javafxapplication4;
import com.bradsbrain.simpleastronomy.MoonPhaseFinder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
//import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import de.mbenning.weather.wunderground.api.domain.DataSet;
import de.mbenning.weather.wunderground.api.domain.WeatherStation;
import de.mbenning.weather.wunderground.api.domain.WeatherStations;
import de.mbenning.weather.wunderground.impl.services.HttpDataReaderService;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import static java.lang.Math.abs;
import static java.lang.System.out;
import java.net.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraintsBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import me.shanked.nicatronTg.jPushover.Pushover;
import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.chrono.IslamicChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import eu.hansolo.enzo.common.Section;
import eu.hansolo.enzo.gauge.SimpleGauge;
import eu.hansolo.enzo.gauge.SimpleGaugeBuilder;
import javafx.stage.StageStyle;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.joda.time.chrono.JulianChronology;
import java.io.IOException;
import java.nio.charset.Charset;
import org.joda.time.Chronology;
import org.joda.time.chrono.ISOChronology;




// test

/**
 *
 * @author ossama
 */
   
    public class JavaFXApplication4 extends Application {
    
    
    
    
     private SimpleGauge thermoMeter;   
        ImageView cameraView,  background;
        ImageView weather_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Weather/nt_mostlycloudy.gif")));      
            
        Group myGroup;
      
    private ProcessBuilder processBuilder_Athan = new ProcessBuilder("bash", "-c", "mpg123 /home/pi/prayertime/Audio/athan1.mp3");
    private ProcessBuilder processBuilder_Duha = new ProcessBuilder("bash", "-c", "mpg123 /home/pi/prayertime/Audio/duha.mp3");
    private ProcessBuilder processBuilder_Tvon = new ProcessBuilder("bash", "-c", "echo \"as\" | cec-client -d 1 -s \"standby 0\" RPI");
    private ProcessBuilder processBuilder1 = new ProcessBuilder("bash", "-c", "echo \"as\" | cec-client -d 1 -s \"standby 0\" RPI");
    private ProcessBuilder processBuilder2 = new ProcessBuilder("bash", "-c", "echo \"standby 0000\" | cec-client -d 1 -s \"standby 0\" RPI");
    private ProcessBuilder processBuilder_camera_on = new ProcessBuilder("bash", "-c", "raspistill -vf -p '25,12,670,480'  -t 5400000 -tl 200000 -w 640 -h 400 -o cam2.jpg");
    private ProcessBuilder processBuilder_camera_off = new ProcessBuilder("bash", "-c", "sudo pkill raspistill");
        
    private double ar_Marquee_Notification_Text_XPos, en_Marquee_Notification_Text_XPos;
    private double ar_Marquee_Notification_Text_YPos, en_Marquee_Notification_Text_YPos;
    private double ar_Marquee_Notification_Text_textSize, en_Marquee_Notification_Text_textSize;
    
    private Toolkit tk;
    private Dimension screenDimension;
    private String en_Marquee_Notification_string, ar_Marquee_Notification_string;
    private Text en_Marquee_Notification_Text, ar_Marquee_Notification_Text;
    
    private Canvas canvas;
    private GraphicsContext canvasgc;
    
    private StringProperty       hour = new SimpleStringProperty();
    private StringProperty       minute = new SimpleStringProperty();
    private StringProperty       second = new SimpleStringProperty();
    private StringProperty       date = new SimpleStringProperty();
    private StringProperty       day_date = new SimpleStringProperty();
    
    
    private final Boolean debug    = false;  //  <<========================== Debuger 
    private final Boolean facebook_image_debug = false; //  <<========================== Debuger 
    private final Boolean auto_friday_cam_debug = false; //  <<========================== Debuger 
    private final Logger logger = Logger.getLogger(JavaFXApplication4.class.getName());
    private Date fullMoon= null; //  <<========================== might fix errors at startup
    private Date newMoon= null; //  <<========================== might fix errors at startup
    private Date date_now;
    private long diff;
    
    final Timeline ar_timeline = new Timeline();
        final Timeline en_timeline = new Timeline();
       final Timeline camera_Timeline = new Timeline();
       
    private FadeTransition facebook_image_fade_in = new FadeTransition(Duration.millis(500));
    private FadeTransition facebook_image_fade_out = new FadeTransition(Duration.millis(500));
    
    private FadeTransition hadith_arabic_fade_out = new FadeTransition(Duration.millis(500));
    private FadeTransition hadith_arabic_fade_in = new FadeTransition(Duration.millis(500));
    private FadeTransition hadith_en_fade_out = new FadeTransition(Duration.millis(500));
    private FadeTransition hadith_en_fade_in = new FadeTransition(Duration.millis(500));
    
    private FadeTransition ft_ar = new FadeTransition(Duration.millis(2000));
    private FadeTransition ft_en = new FadeTransition(Duration.millis(2000));
    
    private Process p;
    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
    
    // Bankstown NSW Location
//    double latitude = -33.9172891;
//    double longitude = 151.035882;
//    double timezone = 10;

    
    
    double latitude;
    double longitude;
    double timezone;
    
    private ObservableList data;
    
    private Integer moonPhase;
    private Boolean isWaning;
    private Boolean sensorLow = false;
    private Boolean hdmiOn = false;
    private Boolean isStarting = true;
    private Boolean new_day = true;
    private Boolean fajr_athan_enable = true ;
    private Boolean duha_athan_enable = true ;
    private Boolean zuhr_athan_enable  = true ;
    private Boolean asr_athan_enable  = true ;
    private Boolean maghrib_athan_enable  = true ;
    private Boolean isha_athan_enable = true ;
    private Boolean fajr_jamma_time_change = false;
    private Boolean zuhr_jamma_time_change = false;
    private Boolean asr_jamma_time_change = false;
    private Boolean maghrib_jamma_time_change = false;
    private Boolean isha_jamma_time_change = false;
    private Boolean notification = false;
    private Boolean prayer_change_notification;
    private Boolean zuhr_custom_notification_set;
    
    private Boolean notification_Bis = false;
    private Boolean athan_Change_Label_visible = false;
    private Boolean notification_Marquee_visible  = false;
    private Boolean update_prayer_labels  = false;
    private Boolean update_moon_image  = false;
    private Boolean getHadith = true;
    private Boolean getFacebook = true;
    private Boolean fajr_jamaat_update_enable = true;
    private Boolean zuhr_jamaat_update_enable = true;
    private Boolean asr_jamaat_update_enable = true;
    private Boolean maghrib_jamaat_update_enable = true;
    private Boolean isha_jamaat_update_enable = true;
    private boolean  notification_Sent;
    private boolean  facebook_Receive = false;
    private boolean  facebook_notification_enable = false;
    private boolean  moon_calcs_display;
    private boolean  show_logo;
    private boolean  vertical = true;
    private boolean  remote_HDMI_control, local_HDMI_control;
    private boolean  pir_disactive_startup = true;
    private boolean arabic = true;
    private boolean english = false;
    private boolean moon_hadith_Label_visible = false;
    private boolean hadith_Label_visible = false;
    private boolean facebook_Label_visible = false;
    private boolean facebook_Text_Post = false;
    private boolean facebook_Picture_Post = false;
    private boolean facebook_Label_visible_set_once = false;
    private boolean prayer_In_Progress = false;
    private boolean startup = true;
    private boolean fajr_prayer_In_Progress_notification = false;
    private boolean zuhr_prayer_In_Progress_notification = false;
    private boolean asr_prayer_In_Progress_notification = false;
    private boolean maghrib_prayer_In_Progress_notification = false;
    private boolean isha_prayer_In_Progress_notification = false;
    private boolean count_down = false;
    private boolean count_down_disable  = false;
    private boolean sonar_active = false;
    private boolean manual_Camera = false;
    private boolean manual_radar_display = false;
    private boolean weather_enabled = false;
    boolean send_Broadcast_msg = false;
    boolean camera = false;
    boolean Button_activated = false;
    boolean radar_displayed = false;
    boolean facebook_turn = false;
    boolean friday_jamaat_cam_activated = false;
    boolean show_friday = false;
    boolean double_friday = false;
    boolean jammat_from_database;
    boolean asr_settime, isha_settime;
    boolean custom_background, zuhr_custom;
    
    boolean isha_ramadan_bool;
   
    boolean weather_retrieve_fault = false;
    boolean weather_image_wrong = false;
    
            
    private String hadith, translated_hadith, ar_full_moon_hadith, en_full_moon_hadith, ar_moon_notification, en_moon_notification, announcement, en_notification_Msg, ar_notification_Msg, device_name, device_location;
    private String ar_notification_Msg_Lines[], en_notification_Msg_Lines[], notification_Msg, facebook_moon_notification_Msg;    
    private String fajr_jamaat ,zuhr_jamaat ,asr_jamaat ,maghrib_jamaat ,isha_jamaat, isha_jamaat_current ;
    private String labeconv;
    private String friday_jamaat, friday2_jamaat, future_zuhr_jamaat_time;
    private String future_fajr_jamaat ,future_zuhr_jamaat ,future_asr_jamaat ,future_maghrib_jamaat ,future_isha_jamaat ;
    private String en_message_String, ar_message_String; 
    private String facebook_post, facebook_post_visibility, facebook_hadith, facebook_Fan_Count, facebook_Post_Url,old_facebook_Post_Url, weather_icon_url;
    private String fb_Access_token, platform,orientation, prayertime_database; 
    private String page_ID;
    String timeZone_ID ; // = timeZone_ID
    String SQL;
    private String hour_in_hour_Label, minute_in_minute_Label;
    private String formattedDateTime;
    private String Weather_icon, weather_image_string;
//    private String cameraSource = "http://192.168.0.6/cam_pic.php";
    
//    private String cameraSource;
            
    
    ResultSet rs;
    
    private int sonar_distance =50000;
    private int sonar_active_distance;
    
    private int id, fajr_adj,asr_adj,maghrib_adj,isha_summer_adj, isha_winter_adj, isha_summer_increment_initial, isha_summer_increment, isha_summer_min_gap;
    private int AsrJuristic,calcMethod;
    private int max_ar_hadith_len, max_en_hadith_len;
    int olddayofweek_int;
    int clock_minute, old_clock_minute ;
    int fajr_diffsec, maghrib_diffsec;
    int fajr_diffsec_dec ,fajr_diffsec_sin, maghrib_diffsec_dec ,maghrib_diffsec_sin;
    private Date prayer_date,future_prayer_date;
    private Calendar fajr_cal, sunrise_cal, duha_cal, zuhr_cal, asr_cal, maghrib_cal, isha_cal,old_today;
    private Calendar fajr_jamaat_cal, duha_jamaat_cal, zuhr_jamaat_cal,friday_jamaat_cal, asr_jamaat_cal, maghrib_jamaat_cal, isha_jamaat_cal;
    private Calendar fajr_jamaat_update_cal, duha_jamaat_update_cal, zuhr_jamaat_update_cal, asr_jamaat_update_cal, maghrib_jamaat_update_cal, isha_jamaat_update_cal;
    private Calendar future_fajr_jamaat_cal, future_zuhr_jamaat_cal, future_asr_jamaat_cal, future_maghrib_jamaat_cal, future_isha_jamaat_cal, maghrib_plus15_cal, zuhr_plus15_cal, zuhr_plus30_cal, friday_plus30_cal;
    private Calendar notification_Date_cal, hadith_notification_Date_cal;
    
    private Date friday1_summer,friday2_summer ,friday1_winter ,friday2_winter ,zuhr_summer ,zuhr_winter,asr_winter, asr_summer, isha_ramadan,isha_summer_start_time, isha_winter;
    
    private Date fajr_begins_time,fajr_jamaat_time, sunrise_time, duha_time, zuhr_begins_time, zuhr_jamaat_time, asr_begins_time, asr_jamaat_time, maghrib_begins_time, maghrib_jamaat_time,isha_begins_time, isha_jamaat_time;
    private Date future_fajr_jamaat_time, future_asr_jamaat_time, future_maghrib_jamaat_time,future_isha_jamaat_time;
    private Date notification_Date, hadith_notification_Date;   
    private Date fullMoon_plus1;
    Date ishadate, maghribdate, asrdate, zuhrdate, fajrdate; 
    private Date fajrjamaatdate, zuhrjamaatdate, asrjamaatdate, maghribjamaatdate, ishajamaatdate;
    
    DateTimeZone tzSAUDI_ARABIA;
    DateTime dtIslamic;
    DateTime DateTime_now;    
    Calendar Calendar_now, Calendar_now_hourpane;
    
    private LocalTime now1;
    
    private Label fajr_hourLeft, fajr_hourRight, fajr_minLeft, fajr_minRight, fajr_jamma_hourLeft, fajr_jamma_hourRight, fajr_jamma_minLeft, fajr_jamma_minRight, footer_Label, like_Label;
    private Label sunrise_hourLeft, sunrise_hourRight, sunrise_minLeft, sunrise_minRight;
    private Label time_Separator1, time_Separator2, time_Separator3, time_Separator4, time_Separator5, time_Separator6,time_Separator8, time_Separator9, time_jamma_Separator1, time_jamma_Separator2, time_jamma_Separator3, time_jamma_Separator4 ,time_jamma_Separator5; 
    private Label zuhr_hourLeft, zuhr_hourRight, zuhr_minLeft, zuhr_minRight, zuhr_jamma_hourLeft, zuhr_jamma_hourRight, zuhr_jamma_minLeft, zuhr_jamma_minRight;
    private Label asr_hourLeft, asr_hourRight, asr_minLeft, asr_minRight, asr_jamma_hourLeft, asr_jamma_hourRight, asr_jamma_minLeft, asr_jamma_minRight;
    private Label maghrib_hourLeft, maghrib_hourRight, maghrib_minLeft, maghrib_minRight, maghrib_jamma_hourLeft, maghrib_jamma_hourRight, maghrib_jamma_minLeft, maghrib_jamma_minRight;
    private Label isha_hourLeft, isha_hourRight, isha_minLeft, isha_minRight, isha_jamma_hourLeft, isha_jamma_hourRight, isha_jamma_minLeft, isha_jamma_minRight;
    private Label friday_hourLeft, friday_hourRight, friday_minLeft, friday_minRight;
    private Label friday2_hourLeft, friday2_hourRight, friday2_minLeft, friday2_minRight;
    private Label Phase_Label, Moon_Date_Label, Sunrise_Date_Label, Moon_Image_Label, Sunrise_Image_Label,Logo_Image_Label,  Weather_Image_Label, Weather_Label1, Weather_Label2,  friday_Label_eng,friday_Label_ar,sunrise_Label_ar,sunrise_Label_eng, fajr_Label_ar, fajr_Label_eng, zuhr_Label_ar, zuhr_Label_eng, asr_Label_ar, asr_Label_eng, maghrib_Label_ar, maghrib_Label_eng, isha_Label_ar, isha_Label_eng, jamaat_Label_eng,jamaat_Label_ar, athan_Label_eng,athan_Label_ar, hadith_Label, announcement_Label,athan_Change_Label_L1, athan_Change_Label_L2, hour_Label,separator_Label, minute_Label, second_Label, date_Label, day_Label, full_Time_Label, divider1_Label, divider2_Label, ar_moon_hadith_Label_L1, ar_moon_hadith_Label_L2, en_moon_hadith_Label_L1, en_moon_hadith_Label_L2, facebook_Label;
    HBox fridayBox2 = new HBox();
    
    
    
                    
    private List<String> images;
    private File directory;
    private File[] files;
    private String rand_Image_Path;
    private int countImages;
    private int imageNumber;
    private int camera_mode_moon_img_width = 30;
    private int camera_mode_moon_img_height = 30;
    int dayofweek_int;
    
    
    private long moonPhase_lastTimerCall,translate_lastTimerCall, clock_update_lastTimerCall ,sensor_lastTimerCall,sonar_lastTimerCall, sensor1_lastTimerCall, debug_lastTimerCall, radarwidget_lastTimerCall, delay_Manual_switch_to_cam_lastTimerCall, weather_lastTimerCall;
                       
                                   
//    public long delay_Manual_switch_to_cam = 30000000000L; // 30 seconds  
    public long delay_Manual_switch_to_cam = 2700000000000L; // 45 minutes
    
    public long delay_weather_check = 30000000000L; // 45 minutes
    
//    public long delay_switch_to_cam = 10000000000L; // 10 seconds
    public long delay_switch_to_cam = 10000000000L; // 10 seconds
    
//    public long delay_switch_back_to_App = 20000000000L; // 20 seconds
    public long delay_switch_back_to_App = 10000000000L; // 10 seconds
    
    public long delay_turnOnTV_after_Prayers = 135000000000L; // 2.25 minute
//    public long delay_turnOnTV_after_Prayers = 60000000000L; // 1 minute
    public long delay_turnOnTV_after_Prayers_nightmode = 420000000000L; // 7 minutes
    
    public long delay_turnOffTV_after_inactivity = 1500000000000L; // 25minutes
//    public long delay_turnOffTV_after_inactivity = 280000000000L; // 1minutes
    private AnimationTimer moonPhase_timer, translate_timer, clock_update_timer ,debug_timer, radar_gauge_anninmation ;
    
        
    DateFormat dateFormat = new SimpleDateFormat("hh:mm");
    
    
    GridPane Glasspane, Mainpane, Moonpane, Logopane, Weatherpane, Sunrisepane, prayertime_pane, clockPane, hadithPane;
    Stage stage;
    Pane text_Box;
    char[] arabicChars = {'٠','١','٢','٣','٤','٥','٦','٧','٨','٩'};
    static String[] suffixes =
    //    0     1     2     3     4     5     6     7     8     9
       { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
    //    10    11    12    13    14    15    16    17    18    19
         "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
    //    20    21    22    23    24    25    26    27    28    29
         "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
    //    30    31
         "th", "st" };
    Connection c,c2 ;
    ObservableList<String> names = FXCollections.observableArrayList();
    private String fromClient;
    private String toClient;
    private ServerSocket server;
    
    DatagramSocket socket, socket1;
    String broadcast_msg;
    byte[] buf1 = new byte[256];
    InetAddress group;
    DatagramPacket packet1;

    Scene scene;
    File file = new File("/home/pi/prayertime/Images/");
    private Text newsFeedText;
    Pushover push = new Pushover("WHq3q48zEFpTqU47Wxygr3VMqoodxc", "skhELgtWRXslAUrYx9yp1s0Os89JTF");
    
    private double toCelcium(double temp) {
		return Math.round((5 * (temp - 32.0)) / 9);
	}
    
    
    
    private static String readAll(Reader rd) throws IOException {

        BufferedReader reader = new BufferedReader(rd);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private static JsonElement getAtPath(JsonElement e, String path) {
        JsonElement current = e;
        String ss[] = path.split("/");
        for (int i = 0; i < ss.length; i++) {
            if(current instanceof com.google.gson.JsonObject){
                current = current.getAsJsonObject().get(ss[i]);
            } else if(current instanceof JsonArray){
                JsonElement jsonElement = current.getAsJsonArray().get(0);
                current = jsonElement.getAsJsonObject().get(ss[i]);
            }
        }
        return current;
    }

    
    
    @Override public void init() throws IOException {
        
        
    
        
            
            
// Twitter ==============================
            
//        String Test = "This is a test message"; 
//        Twitter twitter = TwitterFactory.getSingleton();
//        Status status = null;
//        try {
//             status = twitter.updateStatus(Test);
//        } catch (TwitterException ex) {
//            logger.warn("Unexpected error", e);
//        }
//        System.out.println("Successfully updated the status to [" + status.getText() + "].");
//        try {
//                cameraSource = new File("/home/pi/prayertime/camera/cam2.jpg").toURI().toURL().toString();
//            } catch (MalformedURLException ex) {
//                java.util.logging.Logger.getLogger(JavaFXApplication4.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            
 
            

            
            
            now1 = LocalTime.now();
            radar_gauge_anninmation = new AnimationTimer() {
                @Override public void handle(long now1) {
                    if (now1 > radarwidget_lastTimerCall + 200_000_000l) {
                        
                        thermoMeter.setValue(sonar_distance);
                        radarwidget_lastTimerCall = now1;
                    }
                }
            };
            
            
            
//        Timeline camera_Timeline = new Timeline
//                (
//                    new KeyFrame(Duration.seconds(0), evt -> 
//                {
//
//
//                    Mainpane.getChildren().removeAll(myGroup);      
//                    cameraView = ImageViewBuilder.create()
//                        .image(new Image(cameraSource))
//                        .build();
//                    myGroup = GroupBuilder.create()
//                        .children(cameraView)
//                        .build();
//                    Mainpane.add(myGroup, 0, 0,30,26);
//
////                    System.out.println("Changing Background...");
//
//                }),
//                new KeyFrame(Duration.seconds(0.1))
//            );
//            camera_Timeline.setCycleCount(Animation.INDEFINITE);
//
 

logger.info("Starting prayer application....");



Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
{
    @Override
    public void run()
    {
        logger.info("Exiting application....");
        
        try {Process process2 = processBuilder2.start();}
        catch (IOException e) {logger.warn("Unexpected error", e);}
        System.out.println("Switching back to App...");
        try {Process process = processBuilder_camera_off.start(); }
        catch (IOException e) {logger.warn("Unexpected error", e);}
        
        Platform.exit();
        
    }
}));

moonPhase = 200;

// Get Parameter from database==========================================================

try
{
    c = DBConnect.connect();

//    SQL = "Select * from settings_al_takwa";
//    SQL = "Select * from settings_ESCA";
//    SQL = "Select * from settings";
    SQL = "Select * from settings_MIA";
    
    rs = c.createStatement().executeQuery(SQL);
    while (rs.next())
    {
        id =                            rs.getInt("id");
        platform =                      rs.getString("platform");
        orientation =                   rs.getString("orientation");
        
        //can't be both******************************************
        moon_calcs_display          =   rs.getBoolean("moon_calcs_display"); 
        show_logo        =              rs.getBoolean("show_logo");
        //can't be both*******************************************
        
        custom_background =             rs.getBoolean("custom_background");
        weather_enabled =               rs.getBoolean("weather_enabled");
                
        facebook_notification_enable =  rs.getBoolean("facebook_notification_enable");
        facebook_Receive             =  rs.getBoolean("facebook_Receive");
        prayer_change_notification =    rs.getBoolean("prayer_change_notification");
        latitude =                      rs.getDouble("latitude");
        longitude =                     rs.getDouble("longitude");
        timezone =                      rs.getInt("timezone");
        timeZone_ID =                   rs.getString("timeZone_ID");
        device_name =                   rs.getString("device_name");
        device_location =               rs.getString("device_location");
        
        remote_HDMI_control =           rs.getBoolean("remote_HDMI_control");
        local_HDMI_control =            rs.getBoolean("local_HDMI_control");
        sonar_active =                  rs.getBoolean("sonar_active");
        sonar_active_distance =         rs.getInt("sonar_active_distance");
        Button_activated =              rs.getBoolean("Button_activated");
        calcMethod =                    rs.getInt("calcMethod");
        AsrJuristic =                   rs.getInt("AsrJuristic");
        fb_Access_token =               rs.getString("fb_Access_token");
        page_ID =                       rs.getString("page_ID");
        jammat_from_database =          rs.getBoolean("jammat_from_database");
        prayertime_database =           rs.getString("prayertime_database");
        fajr_adj =                      rs.getInt("fajr_adj");
        asr_settime =                   rs.getBoolean("asr_settime");        
        asr_adj =                       rs.getInt("asr_adj");
        maghrib_adj =                   rs.getInt("maghrib_adj");
        
        
        isha_summer_adj  =              rs.getInt("isha_summer_adj");
        isha_winter_adj  =              rs.getInt("isha_winter_adj");
        isha_ramadan_bool =             rs.getBoolean("isha_ramadan_bool");
        isha_ramadan =                  rs.getTime("isha_ramadan");
        isha_settime   =                rs.getBoolean("isha_settime");
        isha_winter =                   rs.getTime("isha_winter");
        isha_summer_start_time =        rs.getTime("isha_summer_start_time");
        
        isha_summer_increment_initial=  rs.getInt("isha_summer_increment_initial");
        isha_summer_increment =         rs.getInt("isha_summer_increment");
        isha_summer_min_gap=            rs.getInt("isha_summer_min_gap");
                
                
        show_friday =                   rs.getBoolean("show_friday");
        double_friday =                 rs.getBoolean("double_friday");
        friday1_summer =                rs.getTime("friday1_summer");
        friday2_summer =                rs.getTime("friday2_summer");
        friday1_winter =                rs.getTime("friday1_winter");
        friday2_winter =                rs.getTime("friday2_winter");
        zuhr_custom =                   rs.getBoolean("zuhr_custom");
        zuhr_summer =                   rs.getTime("zuhr_summer");
        zuhr_winter =                   rs.getTime("zuhr_winter");
        asr_summer =                   rs.getTime("asr_summer");
        asr_winter =                   rs.getTime("asr_winter");
        
        max_ar_hadith_len =             rs.getInt("max_ar_hadith_len");
        max_en_hadith_len =             rs.getInt("max_en_hadith_len");
        
    
    
    
}
c.close();
System.out.format("Prayertime server running on %s platform\n", platform);
//                System.out.format(" Face Book Notification Enabled: %s \n Face Book Receive posts: %s \n Facebook page ID: %s \n Latitude: %s \n Longitude: %s \n Time Zone: %s \n Calculation Method: %s  \n Asr Juristic: %s \n", facebook_notification_enable, facebook_Receive, page_ID, latitude, longitude, timezone,calcMethod, AsrJuristic );
System.out.format("Device Name is:%s at %s \n", device_name, device_location);
//                System.out.format("Time Zone ID is:%s \n", timeZone_ID);
System.out.format("Orientation:%s \n", orientation);
//System.out.format("jammat_from_database:%s \n", jammat_from_database);
//
//System.out.format("device_location:%s \n", device_location);
//System.out.format("isha_ramadan:%s \n", isha_ramadan);


}
catch (Exception e){logger.warn("Unexpected error", e);}


if (facebook_notification_enable){System.out.println("facebook notification is enabled" );}
if (!facebook_notification_enable){System.out.println("facebook notification is not enabled" );}
if (remote_HDMI_control){System.out.println("Remote HDMI control is enabled" );}
if (local_HDMI_control){System.out.println("Local HDMI control is enabled" );}
if (Button_activated){System.out.println("camera button is enabled" );}

// facebook Client ==========================================================================

//        FacebookClient facebookClient = new DefaultFacebookClient("CAAJRZCld8U30BAMmPyEHDW2tlR07At1vTmtHEmD8iHtiFWx7D2ZBroCVWQfdhxQ7h2Eohv8ZBPRk85vs2r7XC0K4ibGdFNMTkh0mJU8vui9PEnpvENOSAFD2q7CQ7NJXjlyK1yITmcrvZBAZByy4qV7whiAb2a2SN7s23nYvDgMMG3RhdPIakZBLV39pkksjYZD");
FacebookClient facebookClient = new DefaultFacebookClient(fb_Access_token);

// Pushover ==========================================================================

//https://github.com/nicatronTg/jPushover

String temp_msg = device_name + " at "+ device_location + " is starting";
try {push.sendMessage(temp_msg);} catch (IOException e){e.printStackTrace();}




//        test
//        try 
//                                            {
//                                                String pageID = page_ID +"/feed";
//                                                String temporary_msg = " This is an Automated test message";
//                                                facebookClient.publish(pageID, FacebookType.class, Parameter.with("message", temporary_msg));
//                                                
//                                            }
//                                            catch (FacebookException e){logger.warn("Unexpected error", e);} 


//Load random Background image on strtup ===============================================
images = new ArrayList<String>();
//change on osx
if (platform.equals("osx"))
//        {directory = new File("/Users/ossama/Projects/Pi/javafx/prayertime/background/");}
{
    
    if (orientation.equals("vertical") )
    {
        directory = new File("/Users/ossama/Dropbox/Projects/Pi/javafx/prayertime/background/vertical");
    }
    
    else if (orientation.equals("horizontal") )
    {
        directory = new File("/Users/ossama/Dropbox/Projects/Pi/javafx/prayertime/background/horizontal");
    }
    
    else {
        
        if(custom_background)
        {directory = new File("/Users/ossama/Dropbox/Projects/Pi/javafx/prayertime/background/custom");}
        else
        directory = new File("/Users/ossama/Dropbox/Projects/Pi/javafx/prayertime/background/horizontal_HD");
        
    }
    
}
//        {directory = new File("/Users/samia/NetBeansProjects/prayertime_files/background/");}
//change on Pi
if (platform.equals("pi"))
{
    if (orientation.equals("vertical") )
    {
        directory = new File("/home/pi/prayertime/Images/vertical");
    }
    else if (orientation.equals("horizontal") )
    {directory = new File("/home/pi/prayertime/Images/horizontal");}
    
    else
    {
        
        if(custom_background)
        {directory = new File("/home/pi/prayertime/Images/custom");}
        else
        directory = new File("/home/pi/prayertime/Images/horizontal_HD");}
    
}

files = directory.listFiles();
for(File f : files)
{
    if(!f.getName().startsWith(".")){images.add(f.getName());}
}
//        System.out.println(images);
countImages = images.size();
imageNumber = (int) (Math.random() * countImages);
rand_Image_Path = directory + "/"+ images.get(imageNumber);
//        System.out.println(rand_Image_Path);

if (!platform.equals("osx"))
{
    try
    {
        broadcast_msg = "Prayer Time Server Starting";
        socket1 = new DatagramSocket(null);
        socket1.setBroadcast(true);
        buf1 = broadcast_msg.getBytes();
        group = InetAddress.getByName("255.255.255.255");
        packet1 = new DatagramPacket(buf1, buf1.length, group, 8888);
        socket1.send(packet1);
        
    }
    catch(Exception e){System.err.println("Sending failed. " + e.getMessage());}
}



//        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "echo \"as\" | cec-client -d 1 -s \"standby 0\" RPI");
//        Process process = processBuilder.start();
//            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//               System.out.println(line);
//            }
       
//        ProcessBuilder processBuilder_Athan = new ProcessBuilder("bash", "-c", "mpg123 /home/pi/prayertime/Audio/athan1.mp3");
//        try {Process process3 = processBuilder_Athan.start();} 
//            catch (IOException e) {logger.warn("Unexpected error", e);}


Font.loadFont(JavaFXApplication4.class.getResource("Fonts/PTBLARC.TTF").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/BMajidSh.ttf").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/Oldoutsh.ttf").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/BJadidBd.ttf").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/wlm_carton.ttf").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/Arial_Black.ttf").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/Arial_Bold.ttf").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/timeburner_regular.ttf").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/Pinstripe_Limo.ttf").toExternalForm(),30);
Font.loadFont(JavaFXApplication4.class.getResource("Fonts/LateefRegOT.ttf").toExternalForm(),30);


//        tk = Toolkit.getDefaultToolkit();
//        screenDimension = tk.getScreenSize();




data = FXCollections.observableArrayList();


Mainpane = new GridPane();
Glasspane = new GridPane();


footer_Label = new Label();
like_Label = new Label();
Moon_Image_Label = new Label();
Sunrise_Image_Label  = new Label();
Logo_Image_Label   = new Label();
Weather_Image_Label  = new Label();
Phase_Label = new Label();
Moon_Date_Label = new Label();
Sunrise_Date_Label = new Label();
jamaat_Label_eng = new Label();
jamaat_Label_ar = new Label();
athan_Label_eng = new Label();
athan_Label_ar = new Label();
friday_Label_eng = new Label();
friday_Label_ar = new Label();
sunrise_Label_eng = new Label();
sunrise_Label_ar = new Label();
Weather_Label1 = new Label();
Weather_Label2 = new Label();
fajr_Label_ar = new Label();
fajr_Label_eng = new Label();
zuhr_Label_ar = new Label();
zuhr_Label_eng = new Label();
asr_Label_ar = new Label();
asr_Label_eng = new Label();
maghrib_Label_ar = new Label();
maghrib_Label_eng = new Label();
isha_Label_ar = new Label();
isha_Label_eng = new Label();
hadith_Label = new Label();
ar_moon_hadith_Label_L1 = new Label();
ar_moon_hadith_Label_L2 = new Label();
en_moon_hadith_Label_L1 = new Label();
en_moon_hadith_Label_L2 = new Label();
announcement_Label = new Label();
athan_Change_Label_L1 = new Label();
athan_Change_Label_L2 = new Label();



hour_Label = new Label();
minute_Label = new Label();
second_Label = new Label();
separator_Label = new Label();
date_Label = new Label();
day_Label = new Label();
//        divider1_Label = new Label();
//        divider2_Label = new Label();
fajr_hourLeft = new Label();
fajr_hourRight = new Label();
time_Separator1 = new Label();
fajr_minLeft = new Label();
fajr_minRight = new Label();
fajr_jamma_hourLeft = new Label();
fajr_jamma_hourRight = new Label();
time_jamma_Separator1 = new Label();
fajr_jamma_minLeft = new Label();
fajr_jamma_minRight = new Label();
sunrise_hourLeft = new Label();
sunrise_hourRight = new Label();
time_Separator2 = new Label();
sunrise_minLeft = new Label();
sunrise_minRight = new Label();
zuhr_hourLeft = new Label();
zuhr_hourRight = new Label();
time_Separator3 = new Label();
zuhr_minLeft = new Label();
zuhr_minRight = new Label();
zuhr_jamma_hourLeft = new Label();
zuhr_jamma_hourRight = new Label();
time_jamma_Separator2 = new Label();
zuhr_jamma_minLeft = new Label();
zuhr_jamma_minRight = new Label();
asr_hourLeft = new Label();
asr_hourRight = new Label();
time_Separator4 = new Label();
asr_minLeft = new Label();
asr_minRight = new Label();
asr_jamma_hourLeft = new Label();
asr_jamma_hourRight = new Label();
time_jamma_Separator3 = new Label();
asr_jamma_minLeft = new Label();
asr_jamma_minRight = new Label();
maghrib_hourLeft = new Label();
maghrib_hourRight = new Label();
time_Separator5 = new Label();
maghrib_minLeft = new Label();
maghrib_minRight = new Label();
maghrib_jamma_hourLeft = new Label();
maghrib_jamma_hourRight = new Label();
time_jamma_Separator4 = new Label();
maghrib_jamma_minLeft = new Label();
maghrib_jamma_minRight = new Label();
isha_hourLeft = new Label();
isha_hourRight = new Label();
time_Separator6 = new Label();
isha_minLeft = new Label();
isha_minRight = new Label();
isha_jamma_hourLeft = new Label();
isha_jamma_hourRight = new Label();
time_jamma_Separator5 = new Label();
isha_jamma_minLeft = new Label();
isha_jamma_minRight = new Label();
friday_hourLeft = new Label();
friday_hourRight = new Label();
time_Separator8 = new Label();
friday_minLeft = new Label();
friday_minRight = new Label();
facebook_Label = new Label();
friday2_hourRight = new Label();
friday2_hourLeft = new Label();
time_Separator9 = new Label();
friday2_minLeft = new Label();
friday2_minRight = new Label();



athan_Label_ar.setId("prayer-label-arabic");
athan_Label_ar.setText("الأذان");
prayertime_pane.setHalignment(athan_Label_ar,HPos.CENTER) ;
athan_Label_eng.setId("prayer-label-english");
athan_Label_eng.setText("Athan");
prayertime_pane.setHalignment(athan_Label_eng,HPos.CENTER);

jamaat_Label_ar.setId("prayer-label-arabic");
jamaat_Label_ar.setText("الإقامة");
prayertime_pane.setHalignment(jamaat_Label_ar,HPos.CENTER) ;
jamaat_Label_eng.setId("prayer-label-english");
jamaat_Label_eng.setText("Congregation");
prayertime_pane.setHalignment(jamaat_Label_eng,HPos.CENTER);


sunrise_Label_ar.setId("prayer-label-arabic");
sunrise_Label_ar.setText("الشروق");
prayertime_pane.setHalignment(sunrise_Label_ar,HPos.CENTER) ;
sunrise_Label_eng.setId("prayer-label-english");
sunrise_Label_eng.setText("Sunrise");
prayertime_pane.setHalignment(sunrise_Label_eng,HPos.CENTER);

friday_Label_ar.setId("prayer-label-arabic");
friday_Label_ar.setText("الجمعة");
prayertime_pane.setHalignment(friday_Label_ar,HPos.CENTER) ;
friday_Label_eng.setId("prayer-label-english");
friday_Label_eng.setText("Jumu'ah");
prayertime_pane.setHalignment(friday_Label_eng,HPos.CENTER);

isha_Label_ar.setId("prayer-label-arabic");
isha_Label_ar.setText("العشاء");
prayertime_pane.setHalignment(isha_Label_ar,HPos.CENTER) ;
isha_Label_eng.setId("prayer-label-english");
isha_Label_eng.setText("Isha");
prayertime_pane.setHalignment(isha_Label_eng,HPos.CENTER);

maghrib_Label_ar.setId("prayer-label-arabic");
maghrib_Label_ar.setText("المغرب");
prayertime_pane.setHalignment(maghrib_Label_ar,HPos.CENTER) ;
maghrib_Label_eng.setId("prayer-label-english");
maghrib_Label_eng.setText("Maghrib");
prayertime_pane.setHalignment(maghrib_Label_eng,HPos.CENTER);

asr_Label_ar.setId("prayer-label-arabic");
asr_Label_ar.setText("العصر");
GridPane.setHalignment(asr_Label_ar,HPos.CENTER) ;
asr_Label_eng.setId("prayer-label-english");
asr_Label_eng.setText("Asr");
GridPane.setHalignment(asr_Label_eng,HPos.CENTER);

zuhr_Label_ar.setId("prayer-label-arabic");
zuhr_Label_ar.setText("الظهر");
GridPane.setHalignment(zuhr_Label_ar,HPos.CENTER) ;
zuhr_Label_eng.setId("prayer-label-english");
zuhr_Label_eng.setText("Duhr");
GridPane.setHalignment(zuhr_Label_eng,HPos.CENTER);

fajr_Label_ar.setId("prayer-label-arabic");
fajr_Label_ar.setText("الفجر");
GridPane.setHalignment(fajr_Label_ar,HPos.CENTER) ;
GridPane.setValignment(fajr_Label_ar,VPos.CENTER) ;
fajr_Label_eng.setId("prayer-label-english");
fajr_Label_eng.setText("Fajr");
GridPane.setHalignment(fajr_Label_eng,HPos.CENTER);



thermoMeter = SimpleGaugeBuilder.create()
        .minSize(550, 550)
        .sections(new Section(0, 500, "0"),
                new Section(500, 1000),
                new Section(1000, 1500),
                new Section(1500, 2000),
                new Section(2000, 2500),
                new Section(2500, 3000),
                new Section(3000, 3500),
                new Section(3500, 4000),
                new Section(4000, 4500),
                new Section(4500, 5000))
        .sectionTextVisible(true)
        .title("Distance")
//                                        .unit("$")
        .maxValue(5000)
        .value(0)
        .animationDuration(200)
        .animated(true)
        .styleClass(SimpleGauge.STYLE_CLASS_RED_TO_GREEN_10)
        .build();

radarwidget_lastTimerCall = System.nanoTime() + 2_000_000_000l;



Timer prayerCalcTimer = new Timer();
prayerCalcTimer.scheduleAtFixedRate(new TimerTask()
{
    @Override
    public void run()
    {
        try {
            
//                        Moon m = new Moon();
//                        moonPhase = m.illuminatedPercentage();
//                        isWaning = m.isWaning();
//                        update_moon_image = true;
//                        System.out.println("The moon is " + moonPhase + "% full and " + (isWaning ? "waning" : "waxing"));
//

Locale.setDefault(new Locale("en", "AU"));
Date now = new Date();
Calendar cal = Calendar.getInstance();
cal.setTime(now);
//                        cal.add(Calendar.DAY_OF_MONTH, -3);
//                        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
cal.setFirstDayOfWeek(Calendar.MONDAY);
dayofweek_int = cal.get(Calendar.DAY_OF_WEEK);
String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
PrayTime getprayertime = new PrayTime();

getprayertime.setTimeFormat(0);
getprayertime.setCalcMethod(calcMethod);
getprayertime.setAsrJuristic(AsrJuristic);
getprayertime.setAdjustHighLats(0);
int[] offsets = {0, 0, 0, 0, 0, 0, 0}; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
getprayertime.tune(offsets);

Date time = cal.getTime();
//                        System.out.println(" daylight saving? " + TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time ));
                        
//                        The following calculate the next daylight saving date
DateTimeZone zone = DateTimeZone.forID(timeZone_ID);
DateTimeFormatter format = DateTimeFormat.mediumDateTime();

//                        long current = System.currentTimeMillis();
//                        for (int i=0; i < 1; i++)
//                        {
//                            long next = zone.nextTransition(current);
//                            if (current == next)
//                            {
//                                break;
//                            }
//                            System.out.println ("Next Daylight saving Change: " + format.print(next) + " Into DST? " 
//                                                + !zone.isStandardOffset(next));
//                            current = next;
//                        }

long next = zone.nextTransition(System.currentTimeMillis());

Date nextTransitionDate = new Date(next);
Format format1 = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
format1.format(nextTransitionDate).toString();
Calendar nextTransitionCal = Calendar.getInstance();
nextTransitionCal.setTime(nextTransitionDate);
//                        Date fajr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(next);

Calendar nextTransitionCal_min_3 = Calendar.getInstance();
nextTransitionCal_min_3.setTime(nextTransitionDate);
nextTransitionCal_min_3.add(Calendar.DAY_OF_MONTH, -3);
nextTransitionCal_min_3.set(Calendar.HOUR_OF_DAY, 0);
                        System.out.println ("Next Daylight saving Check (-3 days): " + nextTransitionCal_min_3.getTime());



ArrayList<String> prayerTimes = getprayertime.getPrayerTimes(cal, latitude, longitude, timezone);
ArrayList<String> prayerNames = getprayertime.getTimeNames();

SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

Date fajr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(0)).getTime()));
cal.setTime(fajr_temp);
if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){cal.add(Calendar.MINUTE, 60);}
Date fajr = cal.getTime();
fajr_cal = Calendar.getInstance();
fajr_cal.setTime(fajr);
fajr_begins_time = fajr_cal.getTime();
//                        System.out.println(" fajr time " + fajr_begins_time);

Date sunrise_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(1)).getTime()));
cal.setTime(sunrise_temp);
if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){cal.add(Calendar.MINUTE, 60);}
Date sunrise = cal.getTime();
sunrise_cal = Calendar.getInstance();
sunrise_cal.setTime(sunrise);
sunrise_time = sunrise_cal.getTime();
//                        System.out.println(" sunrise time " + sunrise_time);

cal.add(Calendar.MINUTE, 15);
Date duha = cal.getTime();
//                            System.out.println(duha);
duha_cal = Calendar.getInstance();
duha_cal.setTime(duha);
duha_cal.set(Calendar.MILLISECOND, 0);
duha_cal.set(Calendar.SECOND, 0);
//                        System.out.println(" Duha alarm time " + duha_cal.getTime());


Date zuhr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(2)).getTime()));
cal.setTime(zuhr_temp);
if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){cal.add(Calendar.MINUTE, 60);}
Date zuhr = cal.getTime();
zuhr_cal = Calendar.getInstance();
zuhr_cal.setTime(zuhr);
zuhr_begins_time = zuhr_cal.getTime();
//                        System.out.println(" Zuhr time " + zuhr_begins_time);

Date asr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(3)).getTime()));
cal.setTime(asr_temp);
if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){cal.add(Calendar.MINUTE, 60);}
Date asr = cal.getTime();
asr_cal = Calendar.getInstance();
asr_cal.setTime(asr);
asr_begins_time = asr_cal.getTime();
//                        System.out.println(" Asr time " + asr_begins_time);

Date maghrib_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(5)).getTime()));
cal.setTime(maghrib_temp);
if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){cal.add(Calendar.MINUTE, 60);}
Date maghrib = cal.getTime();
maghrib_cal = Calendar.getInstance();
maghrib_cal.setTime(maghrib);
maghrib_begins_time = maghrib_cal.getTime();
//                        System.out.println(" maghrib time " + maghrib_begins_time);

maghrib_plus15_cal = (Calendar)maghrib_cal.clone();
maghrib_plus15_cal.add(Calendar.MINUTE, +15);

Date isha_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(6)).getTime()));
cal.setTime(isha_temp);
if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){cal.add(Calendar.MINUTE, 60);}
Date isha = cal.getTime();
isha_cal = Calendar.getInstance();
isha_cal.setTime(isha);
isha_begins_time = isha_cal.getTime();
//                        System.out.println(" isha time " + isha_begins_time);
                        
//                        set friday prayer here
if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time ))
{
    friday_jamaat = friday1_summer.toString(); 
    
    
    friday2_jamaat = friday2_summer.toString();;
}
else
{
    friday_jamaat = friday1_winter.toString();
    
    
    friday2_jamaat = friday2_winter.toString();
    
    
}

update_prayer_labels = true;
//                        getFacebook = true;

DateTime_now = new DateTime();
Calendar_now = Calendar.getInstance();
Calendar_now.setTime(new Date());
Calendar_now.set(Calendar.MILLISECOND, 0);
Calendar_now.set(Calendar.SECOND, 0);
Calendar_now.set(Calendar.MINUTE, 0);
Calendar_now.set(Calendar.HOUR_OF_DAY, 0);

tzSAUDI_ARABIA = DateTimeZone.forID("Asia/Riyadh");
dtIslamic = DateTime_now.withChronology(IslamicChronology.getInstance(tzSAUDI_ARABIA, IslamicChronology.LEAP_YEAR_15_BASED));
//                        System.out.print("Arabic Date:  ");
//                        System.out.print(dtIslamic.getMonthOfYear());
//                        System.out.print("/");
//                        System.out.println(dtIslamic.getDayOfMonth());

//                        if (dtIslamic.getMonthOfYear()==9){System.out.println("==========Ramadan Moubarik==========");}

//enable athan play time
if (dayofweek_int != olddayofweek_int)
{
    if(moon_calcs_display)
    {
        Moon m = new Moon();
        moonPhase = m.illuminatedPercentage();
        isWaning = m.isWaning();
        update_moon_image = true;
    }
//                            System.out.println("The moon is " + moonPhase + "% full and " + (isWaning ? "waning" : "waxing"));
                    
//                            old_today = Calendar.getInstance();
olddayofweek_int = dayofweek_int;
//                            System.out.println("current day of the week " + dayofweek_int );
//                            System.out.println("old day of the week " + olddayofweek_int );
//                            System.out.println(" cal:  " + cal );
fajr_athan_enable = true;
duha_athan_enable = true;
zuhr_athan_enable = true;
asr_athan_enable = true;
maghrib_athan_enable = true;
isha_athan_enable = true;
getHadith = true;

//==============JAMAA Prayer time
if(jammat_from_database)
{
    
    
    
    try
    {
        c = DBConnect.connect();
        //                            System.out.println("connected");
//        SQL = "select * from ESCA_prayertimes where DATE(date) = DATE(NOW())";
        SQL = "select * from " +  prayertime_database +  " where DATE(date) = DATE(NOW())";
        
//        SQL = "select * from prayertimes where DATE(date) = DATE(NOW())";
        rs = c.createStatement().executeQuery(SQL);
        while (rs.next())
        {
            id =                rs.getInt("id");
            prayer_date =       rs.getDate("date");
            fajr_jamaat_time =       rs.getTime("fajr_jamaat");
            asr_jamaat_time =        rs.getTime("asr_jamaat");
            maghrib_jamaat_time =        rs.getTime("maghrib_jamaat");
            isha_jamaat_time =       rs.getTime("isha_jamaat");
        }
        c.close();
        fajr_jamaat = fajr_jamaat_time.toString();
        
        
        
        if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){zuhr_jamaat = zuhr_summer.toString();} else{zuhr_jamaat = zuhr_winter.toString();}
        asr_jamaat = asr_jamaat_time.toString();
        maghrib_jamaat = maghrib_jamaat_time.toString();
        isha_jamaat = isha_jamaat_time.toString();
        // print the results
        //                                System.out.format("%s,%s,%s,%s,%s \n", id, prayer_date, fajr_jamaat, asr_jamaat, isha_jamaat );
    }
    catch (Exception e){ logger.warn("Unexpected error", e); }
    
    Date fajr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + fajr_jamaat);
    cal.setTime(fajr_jamaat_temp);
    cal.add(Calendar.MINUTE, 5);
    Date fajr_jamaat = cal.getTime();
    fajr_jamaat_update_cal = Calendar.getInstance();
    fajr_jamaat_update_cal.setTime(fajr_jamaat);
    fajr_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
    fajr_jamaat_update_cal.set(Calendar.SECOND, 0);
    
    fajr_jamaat_cal = (Calendar)fajr_jamaat_update_cal.clone();
    fajr_jamaat_cal.add(Calendar.MINUTE, -5);
    //                            System.out.println("fajr Jamaat update scheduled at:" + fajr_jamaat_update_cal.getTime());
    
    
    Date zuhr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + zuhr_jamaat);
    cal.setTime(zuhr_jamaat_temp);
    Date zuhr_jamaat_Date = cal.getTime();
    zuhr_jamaat_cal = Calendar.getInstance();
    zuhr_jamaat_cal.setTime(zuhr_jamaat_Date);
    zuhr_jamaat_cal.set(Calendar.MILLISECOND, 0);
    zuhr_jamaat_cal.set(Calendar.SECOND, 0);
    //                            System.out.println("=============Zuhr Jamaat at:" + zuhr_jamaat_cal.getTime() + "day int is" + dayofweek_int);
    
    zuhr_plus15_cal = (Calendar)zuhr_jamaat_cal.clone();
    zuhr_plus15_cal.add(Calendar.MINUTE, +15);
    
    zuhr_plus30_cal = (Calendar)zuhr_jamaat_cal.clone();
    zuhr_plus30_cal.add(Calendar.MINUTE, +30);
    
    
    
    
    Date friday_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + friday_jamaat);
    cal.setTime(friday_jamaat_temp);
    Date friday_jamaat_jamaat_Date = cal.getTime();
    friday_jamaat_cal = Calendar.getInstance();
    friday_jamaat_cal.setTime(friday_jamaat_jamaat_Date);
    friday_jamaat_cal.set(Calendar.MILLISECOND, 0);
    friday_jamaat_cal.set(Calendar.SECOND, 0);
    //                            System.out.println("=============friday_jamaat Jamaat at:" + friday_jamaat_cal.getTime() + "day int" + dayofweek_int);
    
    friday_plus30_cal = (Calendar)friday_jamaat_cal.clone();
    friday_plus30_cal.add(Calendar.MINUTE, +30);
    
    if(auto_friday_cam_debug)
    {
//                                    System.out.println("=============friday_jamaat Jamaat at:" + friday_jamaat_cal.getTime() + "day int" + dayofweek_int);
        friday_jamaat_cal = Calendar.getInstance();
        friday_jamaat_cal.set(Calendar.MILLISECOND, 0);
        friday_jamaat_cal.set(Calendar.SECOND, 0);
        friday_jamaat_cal.add(Calendar.MINUTE, +2);
        //                                friday_jamaat_cal.set(Calendar.HOUR_OF_DAY, 11);
        
        friday_plus30_cal = (Calendar)friday_jamaat_cal.clone();
        friday_plus30_cal.add(Calendar.MINUTE, +2);
        
        dayofweek_int = 6;
    }
    
    Date asr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + asr_jamaat);
    cal.setTime(asr_jamaat_temp);
    cal.add(Calendar.MINUTE, 5);
    Date asr_jamaat = cal.getTime();
    asr_jamaat_update_cal = Calendar.getInstance();
    asr_jamaat_update_cal.setTime(asr_jamaat);
    asr_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
    asr_jamaat_update_cal.set(Calendar.SECOND, 0);
    //                            System.out.println("asr Jamaat update scheduled at:" + asr_jamaat_update_cal.getTime());
    asr_jamaat_cal = (Calendar)asr_jamaat_update_cal.clone();
    asr_jamaat_cal.add(Calendar.MINUTE, -5);
    
    
    if (maghrib_adj ==60)
    {

        Date maghrib_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + maghrib_jamaat);
        cal.setTime(maghrib_jamaat_temp);
        cal.add(Calendar.MINUTE, 5);
        Date maghrib_jamaat = cal.getTime();
        maghrib_jamaat_update_cal = Calendar.getInstance();
        maghrib_jamaat_update_cal.setTime(maghrib_jamaat);
        maghrib_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
        maghrib_jamaat_update_cal.set(Calendar.SECOND, 0);
        //                            System.out.println("asr Jamaat update scheduled at:" + asr_jamaat_update_cal.getTime());
        maghrib_jamaat_cal = (Calendar)maghrib_jamaat_update_cal.clone();
        maghrib_jamaat_cal.add(Calendar.MINUTE, -5);
    }
    
    else
    {
        maghrib_jamaat_cal = (Calendar)maghrib_cal.clone();
        maghrib_jamaat_cal.add(Calendar.MINUTE, maghrib_adj);

    }
    
    
    
    
    Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat);
    cal.setTime(isha_jamaat_temp);
    cal.add(Calendar.MINUTE, 5);
    Date isha_jamaat = cal.getTime();
    isha_jamaat_update_cal = Calendar.getInstance();
    isha_jamaat_update_cal.setTime(isha_jamaat);
    isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
    isha_jamaat_update_cal.set(Calendar.SECOND, 0);
    //                            System.out.println("Isha Jamaat update scheduled at:" + isha_jamaat_update_cal.getTime());
    isha_jamaat_cal = (Calendar)isha_jamaat_update_cal.clone();
    isha_jamaat_cal.add(Calendar.MINUTE, -5);
    
    
}

else
{
    
    fajr_jamaat_cal = (Calendar)fajr_cal.clone();
    fajr_jamaat_cal.add(Calendar.MINUTE, fajr_adj);
    fajr_jamaat_update_cal = (Calendar)fajr_jamaat_cal.clone();
    fajr_jamaat_update_cal.add(Calendar.MINUTE, 5);
    fajr_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
    fajr_jamaat_update_cal.set(Calendar.SECOND, 0);
    
    if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time ))
    {       
        zuhr_jamaat = zuhr_summer.toString();   
        if (zuhr_custom)
        {
//////////////testing zuhr 5 mins gap////////////////////////
            //        zuhr_cal.set(Calendar.SECOND, 0); 
            //        zuhr_cal.set(Calendar.MINUTE, 12); 
            //        zuhr_cal.set(Calendar.HOUR_OF_DAY, 13); 
            //        zuhr_begins_time = zuhr_cal.getTime();
             ///////////////////////////////////////////////   
            
            Date zuhr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + zuhr_jamaat);
            Calendar zuhr_jamaat_temp_cal =  Calendar.getInstance();
            zuhr_jamaat_temp_cal.setTime(zuhr_jamaat_temp);      
    //        zuhr_jamaat_temp_cal.set(Calendar.HOUR, 13);
            zuhr_jamaat_temp_cal.set(Calendar.AM_PM, Calendar.PM );
            zuhr_jamaat_temp = zuhr_jamaat_temp_cal.getTime();       

            long diff = zuhr_jamaat_temp.getTime() - zuhr_begins_time.getTime();
            long diffMinutes = diff / (60 * 1000) % 60; 
            System.out.print("Zuhr gap:  ");    
            System.out.println(diffMinutes);  
            if (diffMinutes<=5)
            { zuhr_jamaat = "01:20:00";}
        }      
                
    } 
    
    else
    {
        zuhr_jamaat = zuhr_winter.toString();
    }
    Date zuhr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + zuhr_jamaat);
    cal.setTime(zuhr_jamaat_temp);
    Date zuhr_jamaat_Date = cal.getTime();
    zuhr_jamaat_cal = Calendar.getInstance();
    zuhr_jamaat_cal.setTime(zuhr_jamaat_Date);
    zuhr_jamaat_cal.set(Calendar.MILLISECOND, 0);
    zuhr_jamaat_cal.set(Calendar.SECOND, 0);
    //                            System.out.println("=============Zuhr Jamaat at:" + zuhr_jamaat_cal.getTime() + "day int is" + dayofweek_int);
    
    zuhr_plus15_cal = (Calendar)zuhr_jamaat_cal.clone();
    zuhr_plus15_cal.add(Calendar.MINUTE, +15);
    
    zuhr_plus30_cal = (Calendar)zuhr_jamaat_cal.clone();
    zuhr_plus30_cal.add(Calendar.MINUTE, +30);
    
    
    if(asr_settime)
    {
        if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){asr_jamaat = asr_summer.toString();} else{asr_jamaat = asr_winter.toString();}
        Date asr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + asr_jamaat);
        cal.setTime(asr_jamaat_temp);
        Date asr_jamaat_Date = cal.getTime();
        asr_jamaat_cal = Calendar.getInstance();
        asr_jamaat_cal.setTime(asr_jamaat_Date);
        asr_jamaat_cal.set(Calendar.MILLISECOND, 0);
        asr_jamaat_cal.set(Calendar.SECOND, 0);            
            
    }
    
    else
    {
        asr_jamaat_cal = (Calendar)asr_cal.clone();
        asr_jamaat_cal.add(Calendar.MINUTE, asr_adj);
        asr_jamaat_update_cal = (Calendar)asr_jamaat_cal.clone();
        asr_jamaat_update_cal.add(Calendar.MINUTE, 5);
        asr_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
        asr_jamaat_update_cal.set(Calendar.SECOND, 0);
    }
    
    if (maghrib_adj ==60)
    {
        
        try
        {
            c = DBConnect.connect();
            //                            System.out.println("connected");
//            SQL = "select * from ESCA_prayertimes where DATE(date) = DATE(NOW())";
            SQL = "select * from " +  prayertime_database +  " where DATE(date) = DATE(NOW())";
//            SQL = "select * from prayertimes where DATE(date) = DATE(NOW())";
            rs = c.createStatement().executeQuery(SQL);
            while (rs.next())
            {
                maghrib_jamaat_time =        rs.getTime("maghrib_jamaat");
            }
            c.close();
            maghrib_jamaat = maghrib_jamaat_time.toString();
            // print the results
            //                                System.out.format("%s,%s,%s,%s,%s \n", id, prayer_date, fajr_jamaat, asr_jamaat, isha_jamaat );
        }
        catch (Exception e){ logger.warn("Unexpected error", e); }
        
        Date maghrib_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + maghrib_jamaat);
        cal.setTime(maghrib_jamaat_temp);
        cal.add(Calendar.MINUTE, 5);
        Date maghrib_jamaat = cal.getTime();
        maghrib_jamaat_update_cal = Calendar.getInstance();
        maghrib_jamaat_update_cal.setTime(maghrib_jamaat);
        maghrib_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
        maghrib_jamaat_update_cal.set(Calendar.SECOND, 0);
        //                            System.out.println("asr Jamaat update scheduled at:" + asr_jamaat_update_cal.getTime());
        maghrib_jamaat_cal = (Calendar)maghrib_jamaat_update_cal.clone();
        maghrib_jamaat_cal.add(Calendar.MINUTE, -5);
    }
    
    else
    {
        maghrib_jamaat_cal = (Calendar)maghrib_cal.clone();
        maghrib_jamaat_cal.add(Calendar.MINUTE, maghrib_adj);

    }
    
//    System.out.println(dtIslamic.getMonthOfYear());
//    System.out.println(dtIslamic.getDayOfMonth());
    
    Chronology iso = ISOChronology.getInstanceUTC();
    Chronology hijri = IslamicChronology.getInstanceUTC();
    DateTime dtHijri = new DateTime(dtIslamic.getYear(),9,01,22,22,hijri);
    DateTime dtIso = new DateTime(dtHijri, iso);
    Date dtIso_date_minus_one = dtIso.toDate();
    Calendar dtIso_cal_minus_one = Calendar.getInstance();;
    dtIso_cal_minus_one.setTime(dtIso_date_minus_one);
    

    dtIso_cal_minus_one.set(Calendar.MILLISECOND, 0);
    dtIso_cal_minus_one.set(Calendar.SECOND, 0);
    dtIso_cal_minus_one.set(Calendar.MINUTE, 0);
    dtIso_cal_minus_one.set(Calendar.HOUR_OF_DAY, 0);
    System.out.print("Ramadan on: "); 
    System.out.println(dtIso_cal_minus_one.getTime()); 
    dtIso_cal_minus_one.add(Calendar.DAY_OF_MONTH, -1);
    System.out.print("Ramadan -1 day: "); 
    System.out.println(dtIso_cal_minus_one.getTime()); 
                    
                       
    if (isha_ramadan_bool && Calendar_now.compareTo(dtIso_cal_minus_one)==0) 
//    if (isha_ramadan_bool && dtIslamic.getMonthOfYear()==8 && dtIslamic.getDayOfMonth()>=29)
        {
            isha_jamaat = isha_ramadan.toString();
            Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat);
            cal.setTime(isha_jamaat_temp);
            Date isha_jamaat_Date = cal.getTime();
            isha_jamaat_cal = Calendar.getInstance();
            isha_jamaat_cal.setTime(isha_jamaat_Date);
            isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
            isha_jamaat_cal.set(Calendar.SECOND, 0);

            isha_jamaat_update_cal = (Calendar)isha_jamaat_cal.clone();
            isha_jamaat_update_cal.add(Calendar.MINUTE, 5);
            isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
            isha_jamaat_update_cal.set(Calendar.SECOND, 0);

            System.out.println("==========Ramadan Moubarik====Ramadan Isha used (-1 day)======");
        }
        
    else if (isha_ramadan_bool && dtIslamic.getMonthOfYear()==9)
        {
            isha_jamaat = isha_ramadan.toString();
            Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat);
            cal.setTime(isha_jamaat_temp);
            Date isha_jamaat_Date = cal.getTime();
            isha_jamaat_cal = Calendar.getInstance();
            isha_jamaat_cal.setTime(isha_jamaat_Date);
            isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
            isha_jamaat_cal.set(Calendar.SECOND, 0);

            isha_jamaat_update_cal = (Calendar)isha_jamaat_cal.clone();
            isha_jamaat_update_cal.add(Calendar.MINUTE, 5);
            isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
            isha_jamaat_update_cal.set(Calendar.SECOND, 0);

            System.out.println("==========Ramadan Moubarik====Ramadan Isha used======");
        }
        

    else
    {
        
        
        if(isha_settime)
        {
            
            if (!TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time ))
            {                          
                
                isha_jamaat = isha_winter.toString();
                Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat);
                cal.setTime(isha_jamaat_temp);
                Date isha_jamaat_Date = cal.getTime();
                isha_jamaat_cal = Calendar.getInstance();
                isha_jamaat_cal.setTime(isha_jamaat_Date);
                isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
                isha_jamaat_cal.set(Calendar.SECOND, 0);

                isha_jamaat_update_cal = (Calendar)isha_jamaat_cal.clone();
                isha_jamaat_update_cal.add(Calendar.MINUTE, 5);
                isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
                isha_jamaat_update_cal.set(Calendar.SECOND, 0);
            
            }
            
            
            else
            {
////////Debugin
//                isha_cal.add(Calendar.MINUTE, 27);
//                isha_begins_time = isha_cal.getTime();
                
                isha_jamaat_current = isha_summer_start_time.toString();
                Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat_current);
                
                
                Calendar isha_jamaat_temp_cal = Calendar.getInstance();
                isha_jamaat_temp_cal.setTime(isha_jamaat_temp);
                isha_jamaat_temp_cal.set(Calendar.AM_PM, Calendar.PM );
                isha_jamaat_temp = isha_jamaat_temp_cal.getTime();
                
                long diff = isha_jamaat_temp.getTime() - isha_begins_time.getTime();
                long diffMinutes = diff / (60 * 1000) % 60;      
                System.out.print("isha gap from initial");    
                System.out.println(diffMinutes); 
                
//                System.out.print("isha_begins_time");    
//                System.out.println(isha_begins_time); 
//                
//                System.out.print("isha initial");    
//                System.out.println(isha_jamaat_temp); 
                
                
                
                if (diffMinutes>isha_summer_min_gap  )
                {
                    System.out.print("option 1"); 
                    isha_jamaat_cal = Calendar.getInstance();
                    isha_jamaat_cal.setTime(isha_jamaat_temp);
                    isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
                    isha_jamaat_cal.set(Calendar.SECOND, 0);

                    isha_jamaat_update_cal = (Calendar)isha_jamaat_cal.clone();
                    isha_jamaat_update_cal.add(Calendar.MINUTE, 5);
                    isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
                    isha_jamaat_update_cal.set(Calendar.SECOND, 0);
                }
                else if (diffMinutes<=isha_summer_min_gap && diffMinutes>=0 || (diffMinutes<=-1 && diffMinutes>=-20))
                {
                    System.out.print("option 2"); 
                    isha_jamaat_cal = Calendar.getInstance();
                    isha_jamaat_cal.setTime(isha_jamaat_temp);
                    isha_jamaat_cal.add(Calendar.MINUTE, isha_summer_increment_initial );
                    isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
                    isha_jamaat_cal.set(Calendar.SECOND, 0);

                    isha_jamaat_update_cal = (Calendar)isha_jamaat_cal.clone();
                    isha_jamaat_update_cal.add(Calendar.MINUTE, 5);
                    isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
                    isha_jamaat_update_cal.set(Calendar.SECOND, 0);
                }
                
                
                
                else if (diffMinutes<-20 && diffMinutes>=-35)
                {
                    isha_jamaat_cal = Calendar.getInstance();
                    isha_jamaat_cal.setTime(isha_jamaat_temp);
                    System.out.print("option 3");  
                    int add = isha_summer_increment_initial + isha_summer_increment;
                    isha_jamaat_cal.add(Calendar.MINUTE, add );
                    isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
                    isha_jamaat_cal.set(Calendar.SECOND, 0);

                    isha_jamaat_update_cal = (Calendar)isha_jamaat_cal.clone();
                    isha_jamaat_update_cal.add(Calendar.MINUTE, 5);
                    isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
                    isha_jamaat_update_cal.set(Calendar.SECOND, 0);

                }
  
            }
        }
           
        else
        {
            isha_jamaat_cal = (Calendar)isha_cal.clone();
            if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){isha_jamaat_cal.add(Calendar.MINUTE, isha_summer_adj);} else{isha_jamaat_cal.add(Calendar.MINUTE, isha_winter_adj);}

            isha_jamaat_update_cal = (Calendar)isha_jamaat_cal.clone();
            isha_jamaat_update_cal.add(Calendar.MINUTE, 5);
            isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
            isha_jamaat_update_cal.set(Calendar.SECOND, 0);
        }
    }
    
    
    
    
    
    Date friday_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + friday_jamaat);
    cal.setTime(friday_jamaat_temp);
    Date friday_jamaat_jamaat_Date = cal.getTime();
    friday_jamaat_cal = Calendar.getInstance();
    friday_jamaat_cal.setTime(friday_jamaat_jamaat_Date);
    friday_jamaat_cal.set(Calendar.MILLISECOND, 0);
    friday_jamaat_cal.set(Calendar.SECOND, 0);
    //                            System.out.println("=============friday_jamaat Jamaat at:" + friday_jamaat_cal.getTime() + "day int" + dayofweek_int);
    
    friday_plus30_cal = (Calendar)friday_jamaat_cal.clone();
    friday_plus30_cal.add(Calendar.MINUTE, +30);
    
    if(auto_friday_cam_debug)
    {
//                                    System.out.println("=============friday_jamaat Jamaat at:" + friday_jamaat_cal.getTime() + "day int" + dayofweek_int);
        friday_jamaat_cal = Calendar.getInstance();
        friday_jamaat_cal.set(Calendar.MILLISECOND, 0);
        friday_jamaat_cal.set(Calendar.SECOND, 0);
        friday_jamaat_cal.add(Calendar.MINUTE, +2);
        //                                friday_jamaat_cal.set(Calendar.HOUR_OF_DAY, 11);
        
        friday_plus30_cal = (Calendar)friday_jamaat_cal.clone();
        friday_plus30_cal.add(Calendar.MINUTE, +2);
        
        dayofweek_int = 6;
    }
//


}



//==============Prayer time change notification logic + 7days
// check excel file in documentation folder for a flow chart
if(jammat_from_database)
{
    // check if a notification has already been sent, to avoid flooding users with notifications, i.e during a system restart
    ar_notification_Msg_Lines = null;
    
    try
    {
        c = DBConnect.connect();
        SQL = "Select * from notification where id = (select max(id) from notification)";
        rs = c.createStatement().executeQuery(SQL);
        while (rs.next())
        {
            id =                rs.getInt("id");
            notification_Date = rs.getDate("notification_Date");
            en_message_String = rs.getString("en_message_String");
            ar_message_String = rs.getString("ar_message_String");
            notification_Sent = rs.getBoolean("notification_Sent");
        }
        c.close();
//                                    System.out.format("%s,%s,%s,%s \n", notification_Date, en_message_String, ar_message_String, notification_Sent );
    }
    catch (Exception e){logger.warn("Unexpected error", e);}
    notification_Date_cal = Calendar.getInstance();
    notification_Date_cal.setTime(notification_Date);
    notification_Date_cal.set(Calendar.MILLISECOND, 0);
    notification_Date_cal.set(Calendar.SECOND, 0);
    
    //                            System.out.println("notification_Date_cal:" + notification_Date_cal.getTime());
    //                            System.out.println("Calendar_now:         " + Calendar_now.getTime());
    
    if (Calendar_now.compareTo(notification_Date_cal) <0 )  //&& !notification_Sent
    {
        en_notification_Msg = en_message_String;
        ar_notification_Msg = ar_message_String;
        ar_notification_Msg_Lines = ar_notification_Msg.split("\\r?\\n");
        en_notification_Msg_Lines = en_notification_Msg.split("\\r?\\n");
        
        en_Marquee_Notification_string = "Prayer Time Change from " + new SimpleDateFormat("EEEE").format(notification_Date) +":   " + en_notification_Msg_Lines[1];
        ar_Marquee_Notification_string = "إبتداءا من يوم " + new SimpleDateFormat(" EEEE  ", new Locale("ar")).format(notification_Date) + "ستتغير اوقات الصلاة   " + ar_notification_Msg_Lines[1];
        
//                                    System.out.format(en_Marquee_Notification_string);
//                                    System.out.format(ar_Marquee_Notification_string);

    notification_Marquee_visible = true;
//                                athan_Change_Label_visible = true;
//                                getFacebook = false;
        }

    if (Calendar_now.compareTo(notification_Date_cal) >=0 )  //&& !notification_Sent
    {
        athan_Change_Label_visible = false;
        notification_Marquee_visible = false;
        try
        {
            c = DBConnect.connect();
//            SQL = "select * from prayertimes where DATE(date) = DATE(NOW() ) + INTERVAL 3 DAY ";
            SQL = "select * from " +  prayertime_database +  " where DATE(date) = DATE(NOW() ) + INTERVAL 3 DAY ";

            rs = c.createStatement().executeQuery(SQL);
            while (rs.next())
            {
                future_prayer_date =       rs.getDate("date");
                future_fajr_jamaat_time =       rs.getTime("fajr_jamaat");
                future_asr_jamaat_time =        rs.getTime("asr_jamaat");
                future_maghrib_jamaat_time =        rs.getTime("maghrib_jamaat");
                future_isha_jamaat_time =       rs.getTime("isha_jamaat");
            }
            c.close();

            // print the results
//                                            System.out.format("%s,%s,%s,%s \n", future_prayer_date, future_fajr_jamaat_time, future_asr_jamaat_time, future_isha_jamaat_time );


        if (!fajr_jamaat_time.equals(future_fajr_jamaat_time))
        {

        //                                            System.out.println("Fajr Prayer Time Difference" );
            fajr_jamma_time_change =true;
            notification = true;

            java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
            c = DBConnect.connect();
            PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
            ps.setDate(1, sqlDate);
            ps.executeUpdate();
            c.close();
        }


        if (!asr_jamaat_time.equals(future_asr_jamaat_time) )
        {
        //                                            System.out.println("asr Prayer Time Difference" );
            asr_jamma_time_change =true;
            if(!notification)
            {
                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                c = DBConnect.connect();
                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                ps.setDate(1, sqlDate);
                ps.executeUpdate();
                c.close();
            }
            notification = true;
        }

        if (!maghrib_jamaat_time.equals(future_maghrib_jamaat_time) && maghrib_adj == 60)
        {
        //                                            System.out.println("asr Prayer Time Difference" );
            maghrib_jamma_time_change =true;
            if(!notification)
            {
                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                c = DBConnect.connect();
                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                ps.setDate(1, sqlDate);
                ps.executeUpdate();
                c.close();
            }
            notification = true;
            }


            if (!isha_jamaat_time.equals(future_isha_jamaat_time) )
            {
            //                                            System.out.println("isha Prayer Time Difference" );
                isha_jamma_time_change =true;
                if(!notification)
                {
                    java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                    c = DBConnect.connect();
                    PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                    ps.setDate(1, sqlDate);
                    ps.executeUpdate();
                    c.close();
                }
                notification = true;
            }
            }
            catch (Exception e){logger.warn("Unexpected error", e);}

        }
}

else
{
    if(prayer_change_notification)
    {
                
        // check if a notification has already been sent, to avoid flooding users with notifications, i.e during a system restart
        ar_notification_Msg_Lines = null;

        try
        {
            c = DBConnect.connect();
            SQL = "Select * from notification where id = (select max(id) from notification)";
            rs = c.createStatement().executeQuery(SQL);
            while (rs.next())
            {
                id =                rs.getInt("id");
                notification_Date = rs.getDate("notification_Date");
                en_message_String = rs.getString("en_message_String");
                ar_message_String = rs.getString("ar_message_String");
                notification_Sent = rs.getBoolean("notification_Sent");
            }
            c.close();
    //                                    System.out.format("%s,%s,%s,%s \n", notification_Date, en_message_String, ar_message_String, notification_Sent );
        }
        catch (Exception e){logger.warn("Unexpected error", e);}
        if(notification_Date!= null)
        {
            notification_Date_cal = Calendar.getInstance();
            notification_Date_cal.setTime(notification_Date);
            notification_Date_cal.set(Calendar.MILLISECOND, 0);
            notification_Date_cal.set(Calendar.SECOND, 0);
        }

        //                            System.out.println("notification_Date_cal:" + notification_Date_cal.getTime());
        //                            System.out.println("Calendar_now:         " + Calendar_now.getTime());

        if (notification_Date_cal!= null && Calendar_now.compareTo(notification_Date_cal) <0 )  //&& !notification_Sent
        {
            System.out.println(" ****************displaying database notification");
            en_notification_Msg = en_message_String;
            ar_notification_Msg = ar_message_String;
            ar_notification_Msg_Lines = ar_notification_Msg.split("\\r?\\n");
            en_notification_Msg_Lines = en_notification_Msg.split("\\r?\\n");

            en_Marquee_Notification_string = "Prayer Time Change from " + new SimpleDateFormat("EEEE").format(notification_Date) +":   " + en_notification_Msg_Lines[1];
            ar_Marquee_Notification_string = "إبتداءا من يوم " + new SimpleDateFormat(" EEEE  ", new Locale("ar")).format(notification_Date) + "ستتغير اوقات الصلاة   " + ar_notification_Msg_Lines[1];

                                        System.out.format(en_Marquee_Notification_string);
                                        System.out.format(ar_Marquee_Notification_string);

            notification_Marquee_visible = true;
            
    //                                athan_Change_Label_visible = true;
    //                                getFacebook = false;
        }

        else 
        {
            System.out.println(" ****************no database notification is not available, will now calculate prayer changes");
            athan_Change_Label_visible = false;
            notification_Marquee_visible = false;
        
            prayerTimes = getprayertime.getPrayerTimes(nextTransitionCal, latitude, longitude, timezone);
            prayerNames = getprayertime.getTimeNames();

            formatter = new SimpleDateFormat("HH:mm");


            //                        System.out.println(" fajr time " + fajr_begins_time);

            //Test this other masjid

    //debug//////////////////////////////
//            Calendar_now = (Calendar)nextTransitionCal_min_3.clone();
    /////////////////////////////////////
            if(Calendar_now.compareTo(nextTransitionCal_min_3)==0 )
            {
                future_prayer_date = nextTransitionCal.getTime();
                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                c = DBConnect.connect();
                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                ps.setDate(1, sqlDate);
                ps.executeUpdate();
                c.close();
                if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time ))   // notification going to winter
                {
                    //will this fit in notification pane?????

                    //fajr, asr, maghrib, isha
                    notification = true;


                    fajr_jamma_time_change =true;
                    Date future_fajr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(0)).getTime()));
                    cal.setTime(future_fajr_temp);
                    cal.add(Calendar.MINUTE, fajr_adj);
                    future_fajr_jamaat_time = cal.getTime();

                    asr_jamma_time_change =true;
                    if(asr_settime)
                    {
                        asr_jamaat = asr_winter.toString();
                        Date asr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + asr_jamaat);
                        cal.setTime(asr_jamaat_temp);
                        future_asr_jamaat_time = cal.getTime();          
                    }

                    else
                    {
                        Date future_asr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(3)).getTime()));
                        cal.setTime(future_asr_temp);
                        cal.add(Calendar.MINUTE, asr_adj);
                        future_asr_jamaat_time = cal.getTime();
                    } 

                    maghrib_jamma_time_change =true;
                    maghrib_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(5)).getTime()));
                    cal.setTime(maghrib_temp);
                    cal.add(Calendar.MINUTE, maghrib_adj);
                    future_maghrib_jamaat_time = cal.getTime();
                    //                        System.out.println(" maghrib time " + maghrib_begins_time);

                    isha_jamma_time_change =true;
                    if(isha_settime)
                    {
                        isha_jamaat = isha_winter.toString();
                        Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat);
                        cal.setTime(isha_jamaat_temp);
                        future_isha_jamaat_time = cal.getTime();          
                    }

                    else
                    {
                        Date future_isha_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(6)).getTime()));
                        cal.setTime(future_isha_temp);
                        cal.add(Calendar.MINUTE, isha_winter_adj);
                        future_isha_jamaat_time = cal.getTime();
                    } 


                }

                else //going to summer
                {
                    

                    //fajr, zuhr, asr, maghrib, isha
                    fajr_jamma_time_change =true;
                    notification = true;
                    Date future_fajr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(0)).getTime()));
                    cal.setTime(future_fajr_temp);
                    cal.add(Calendar.MINUTE, 60);
                    cal.add(Calendar.MINUTE, fajr_adj);
                    future_fajr_jamaat_time = cal.getTime();

                    asr_jamma_time_change =true;
                    if(asr_settime)
                    {
                        asr_jamaat = asr_summer.toString();
                        Date asr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + asr_jamaat);
                        cal.setTime(asr_jamaat_temp);
                        future_asr_jamaat_time = cal.getTime();          
                    }

                    else
                    {
                        Date future_asr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(3)).getTime()));
                        cal.setTime(future_asr_temp);
                        cal.add(Calendar.MINUTE, asr_adj);
                        cal.add(Calendar.MINUTE, 60);
                        future_asr_jamaat_time = cal.getTime();
                    } 

                    maghrib_jamma_time_change =true;
                    maghrib_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(5)).getTime()));
                    cal.setTime(maghrib_temp);
                    cal.add(Calendar.MINUTE, 60);
                    cal.add(Calendar.MINUTE, maghrib_adj);
                    future_maghrib_jamaat_time = cal.getTime();
                    //                        System.out.println(" maghrib time " + maghrib_begins_time);


                    isha_jamma_time_change =true;
                    if(isha_settime)
                    {
                        isha_jamaat = isha_summer_start_time.toString();
                        Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat);
                        cal.setTime(isha_jamaat_temp);
                        future_isha_jamaat_time = cal.getTime();          
                    }

                    else
                    {
                        Date future_isha_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(6)).getTime()));
                        cal.setTime(future_isha_temp);
                        cal.add(Calendar.MINUTE, isha_summer_adj);
                        cal.add(Calendar.MINUTE, 60);
                        future_isha_jamaat_time = cal.getTime();
                    } 
                    
                    

                }
                zuhr_custom_notification_set = false;
            }

            else 
            //only if custom  i.e. isha, zuhr for MIA??
            {
                Calendar future_prayer_cal = Calendar.getInstance();
                future_prayer_cal.add(Calendar.DAY_OF_MONTH, +3);
                System.out.println ("looking for changes in the next 3 days : " + future_prayer_cal.getTime());
                prayerTimes = getprayertime.getPrayerTimes(future_prayer_cal, latitude, longitude, timezone);
                prayerNames = getprayertime.getTimeNames();
                future_prayer_date = future_prayer_cal.getTime();
                System.out.println(" future prayer date change " + future_prayer_date);
                
                if (isha_settime && TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time ))
                {
                    
                    
                    formatter = new SimpleDateFormat("HH:mm");
                    Date future_isha_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(6)).getTime()));
                    Calendar future_isha_cal = future_prayer_cal.getInstance();
                    future_isha_cal.setTime(future_isha_temp);
                    future_isha_cal.add(Calendar.MINUTE, 60);
//                    future_isha_cal.add(Calendar.DAY_OF_MONTH, -3);
                    Date future_isha_time = future_isha_cal.getTime();

                    System.out.println(" future isha time " + future_isha_time);
                    
        ////////Debugin
        //                isha_cal.add(Calendar.MINUTE, 27);
        //                isha_begins_time = isha_cal.getTime();

                    isha_jamaat_current = isha_summer_start_time.toString();
                    
                    
                    
                    Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat_current);
                    
                    System.out.println("  isha jamaa temp  " + isha_jamaat_temp);
                    
                    
                    
                    diff = isha_jamaat_temp.getTime() - future_isha_time.getTime();
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000);
                    System.out.println(diffHours); 
                    System.out.print("future isha gap from initial: ");    
                    System.out.println(diffMinutes);  
                    
                    if (diffMinutes>isha_summer_min_gap)
                    {

                        future_isha_jamaat_cal = future_prayer_cal.getInstance();
                        future_isha_jamaat_cal.setTime(isha_jamaat_temp);
                        future_isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
                        future_isha_jamaat_cal.set(Calendar.SECOND, 0);
                        future_isha_jamaat_time = future_isha_jamaat_cal.getTime();
                        
                        isha_jamaat_time = isha_jamaat_cal.getTime();
                        long diff1 = isha_jamaat_time.getTime() - future_isha_jamaat_time.getTime();
                        
                        if(diff1 != 0)
                        {
                            System.out.println("future isha change 1 detected in 3 days");
                            isha_jamma_time_change =true;
                            if(!notification)
                            {
                                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                                c = DBConnect.connect();
                                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                                ps.setDate(1, sqlDate);
                                ps.executeUpdate();
                                c.close();
                            }
                            notification = true;
                            
                        }

                        
                    }
                    
                    
                    else if (diffMinutes<=isha_summer_min_gap && diffMinutes>=0 && diffHours==0 || (diffMinutes<=-1 && diffMinutes>=-20))
                    {                        
                        future_isha_jamaat_cal = future_prayer_cal.getInstance();
                        future_isha_jamaat_cal.setTime(isha_jamaat_temp);
                        future_isha_jamaat_cal.add(Calendar.MINUTE, isha_summer_increment_initial );
                        future_isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
                        future_isha_jamaat_cal.set(Calendar.SECOND, 0);
                        future_isha_jamaat_time = future_isha_jamaat_cal.getTime();
                        
                        isha_jamaat_time = isha_jamaat_cal.getTime();
                        long diff1 = isha_jamaat_time.getTime() - future_isha_jamaat_time.getTime();
                        
                        if(diff1 != 0)
                        {
                            System.out.println("future isha change 1 detected in 3 days");
                            isha_jamma_time_change =true;
                            if(!notification)
                            {
                                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                                c = DBConnect.connect();
                                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                                ps.setDate(1, sqlDate);
                                ps.executeUpdate();
                                c.close();
                            }
                            notification = true;
                            
                        }
                        
                    }

                    else if (diffMinutes<-20 && diffMinutes>=-35)
                    {
                        isha_jamma_time_change =true;
                        future_isha_jamaat_cal = future_prayer_cal.getInstance();
                        future_isha_jamaat_cal.setTime(isha_jamaat_temp);
                        int add = isha_summer_increment_initial + isha_summer_increment;
                        future_isha_jamaat_cal.add(Calendar.MINUTE, add);
                        future_isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
                        future_isha_jamaat_cal.set(Calendar.SECOND, 0);
                        future_isha_jamaat_time = future_isha_jamaat_cal.getTime();
                        
                        isha_jamaat_time = isha_jamaat_cal.getTime();
                        long diff1 = isha_jamaat_time.getTime() - future_isha_jamaat_time.getTime();
                        System.out.println(diff1);
                        
                        if(diff1 != 0)
                        {
                            System.out.println("future isha change 2 detected in 3 days");
                            isha_jamma_time_change =true;
                            if(!notification)
                            {
                                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                                c = DBConnect.connect();
                                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                                ps.setDate(1, sqlDate);
                                ps.executeUpdate();
                                c.close();
                            }
                            notification = true;
                        }
                    }
                }
                
                if (isha_ramadan_bool)
                {
                    
                    Chronology iso = ISOChronology.getInstanceUTC();
                    Chronology hijri = IslamicChronology.getInstanceUTC();
                    DateTime dtHijri = new DateTime(dtIslamic.getYear(),9,01,22,22,hijri);

                    DateTime dtIso = new DateTime(dtHijri, iso);
                    System.out.println(dtIso);
                    Date dtIso_date = dtIso.toDate();
                    Calendar dtIso_cal = Calendar.getInstance();;
                    dtIso_cal.setTime(dtIso_date);
                    
                    Date dtIso_date_minus_four = dtIso.toDate();
                    Calendar dtIso_cal_minus_four = Calendar.getInstance();;
                    dtIso_cal_minus_four.setTime(dtIso_date_minus_four);
                    dtIso_cal_minus_four.add(Calendar.DAY_OF_MONTH, -4);
                    
                    dtIso_cal_minus_four.set(Calendar.MILLISECOND, 0);
                    dtIso_cal_minus_four.set(Calendar.SECOND, 0);
                    dtIso_cal_minus_four.set(Calendar.MINUTE, 0);
                    dtIso_cal_minus_four.set(Calendar.HOUR_OF_DAY, 0);
                    System.out.println(dtIso_cal_minus_four.getTime()); 
                    System.out.print("Ramadan -4 day: "); 
                    System.out.println(dtIso_cal_minus_four.getTime());
                    
                    Date dtIso_date_minus_one = dtIso.toDate();
                    Calendar dtIso_cal_minus_one = Calendar.getInstance();
                    dtIso_cal_minus_one.setTime(dtIso_date_minus_one);


                    dtIso_cal_minus_one.set(Calendar.MILLISECOND, 0);
                    dtIso_cal_minus_one.set(Calendar.SECOND, 0);
                    dtIso_cal_minus_one.set(Calendar.MINUTE, 0);
                    dtIso_cal_minus_one.set(Calendar.HOUR_OF_DAY, 0);
                    dtIso_cal_minus_one.add(Calendar.DAY_OF_MONTH, -1);
                    
                    
                       
                    if (Calendar_now.compareTo(dtIso_cal_minus_four)==0  ) 
                    {
                        
//                        DateTime future_prayer_date_dateTime  = dtIslamic.withChronology(ISOChronology.getInstance()) ;
//                        future_prayer_date = future_prayer_date_dateTime.toDate();
                        
                        future_prayer_date = dtIso_cal_minus_one.getTime();
                         
                        isha_jamaat = isha_ramadan.toString();
                        Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + isha_jamaat);
                        cal.setTime(isha_jamaat_temp);
                        future_isha_jamaat_time = cal.getTime();
                        Calendar isha_ramadan_cal;
                        isha_ramadan_cal = Calendar.getInstance();
                        isha_ramadan_cal.setTime(future_isha_jamaat_time);
                        
                        
                        if(isha_ramadan_cal.compareTo(isha_jamaat_cal)<0 || isha_ramadan_cal.compareTo(isha_jamaat_cal)>0)
                        {   
                            System.out.println("Ramadan Isha notification");
                        
                            isha_jamma_time_change =true;
                            if(!notification)
                            {
                                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                                c = DBConnect.connect();
                                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                                ps.setDate(1, sqlDate);
                                ps.executeUpdate();
                                c.close();
                            }
                            notification = true;
                        }
                        
                    }
                }

                        
                        

                    

//                    else if (diffMinutes<-35 && diffMinutes>=-50)
//                    {
//                        isha_jamma_time_change =true;
//                        future_isha_jamaat_cal = future_prayer_cal.getInstance();
//                        future_isha_jamaat_cal.setTime(isha_jamaat_temp);
//                        int add = isha_summer_increment_initial + isha_summer_increment + isha_summer_increment;
//                        future_isha_jamaat_cal.add(Calendar.MINUTE, add);
//                        future_isha_jamaat_cal.set(Calendar.MILLISECOND, 0);
//                        future_isha_jamaat_cal.set(Calendar.SECOND, 0);
//                        future_isha_jamaat_time = future_isha_jamaat_cal.getTime();
//                        
//                        isha_jamaat_time = isha_jamaat_cal.getTime();
//                        long diff1 = isha_jamaat_time.getTime() - future_isha_jamaat_time.getTime();
//                        System.out.println(diff1);
//                        
//                        if(diff1 != 0)
//                        {
//                            System.out.println("future isha change detected in 3 days");
//                            isha_jamma_time_change =true;
//                            if(!notification)
//                            {
//                                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
//                                c = DBConnect.connect();
//                                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
//                                ps.setDate(1, sqlDate);
//                                ps.executeUpdate();
//                                c.close();
//                            }
//                            notification = true;
//                        }
//                        
//                    }


                if (zuhr_custom)
                {
                    //When athan goes 1:10 move iqamah to 1:20
                    
                    if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time ))
                    {                                       
                                
                        formatter = new SimpleDateFormat("HH:mm");
                        Date future_zuhr_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + new Time(formatter.parse(prayerTimes.get(2)).getTime()));
                        cal.setTime(future_zuhr_temp);
                        cal.add(Calendar.MINUTE, 60);
                        future_zuhr_temp = cal.getTime();

                        System.out.println(" future zuhr time " + future_zuhr_temp);

                        Date zuhr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + zuhr_jamaat);
                        Calendar zuhr_jamaat_temp_cal =  Calendar.getInstance();
                        zuhr_jamaat_temp_cal.setTime(zuhr_jamaat_temp);      
                //        zuhr_jamaat_temp_cal.set(Calendar.HOUR, 13);
                        zuhr_jamaat_temp_cal.set(Calendar.AM_PM, Calendar.PM );
                        zuhr_jamaat_temp = zuhr_jamaat_temp_cal.getTime();       

                        long diff = zuhr_jamaat_temp.getTime() - future_zuhr_temp.getTime();
                        long diffMinutes = diff / (60 * 1000) % 60; 
                        System.out.print("future Zuhr gap:  ");    
                        System.out.println(diffMinutes);  
                        
//                        compare future_zuhr_jamaat and zuhr_jamaat
                                
                                
                        if (diffMinutes<=5)
                        { 
                            zuhr_jamma_time_change =true;
                            future_zuhr_jamaat = "01:20";
                            zuhr_custom_notification_set = true;
                            if(!notification)
                            {
                                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                                c = DBConnect.connect();
                                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                                ps.setDate(1, sqlDate);
                                ps.executeUpdate();
                                c.close();
                            }
                            notification = true;

                        }
                        else if (diffMinutes==11)
                        { 
                            
                            
                            
                            System.out.print("******zuhr notification to 1:15");          
                            zuhr_jamma_time_change =true;
                            future_zuhr_jamaat = "01:15";
                            zuhr_custom_notification_set = true;
                            if(!notification)
                            {
                                java.sql.Date sqlDate = new java.sql.Date(future_prayer_date.getTime());
                                c = DBConnect.connect();
                                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.notification (notification_Date) VALUE (?)");
                                ps.setDate(1, sqlDate);
                                ps.executeUpdate();
                                c.close();
                            }
                            notification = true;

                        }
                        else zuhr_custom_notification_set = false;
            
                        
                //////////////testing zuhr 5 mins gap////////////////////////
                            //        zuhr_cal.set(Calendar.SECOND, 0); 
                            //        zuhr_cal.set(Calendar.MINUTE, 12); 
                            //        zuhr_cal.set(Calendar.HOUR_OF_DAY, 13); 
                            //        zuhr_begins_time = zuhr_cal.getTime();
                             ///////////////////////////////////////////////   

                        
                             

                    } 
                    

                }

                

            }







            }
    }


}
  

// Prayer time change notification/////////////////////put this in a thread, so error does not stop code further down ========================================================
// creates message to send to facebook
// creates labels for notification

if (notification)
{
ar_notification_Msg_Lines = null;
//                            Calendar_now.setTime(future_prayer_date);
Calendar prayertime_Change_Due_Date = null;
prayertime_Change_Due_Date = Calendar.getInstance();
prayertime_Change_Due_Date.setTime(future_prayer_date);

//                            System.out.println ("Calendar_now: " + Calendar_now.getTime());
int day = prayertime_Change_Due_Date.get(Calendar.DAY_OF_MONTH);
String dayStr = day + suffixes[day];
String en_notification_date = new SimpleDateFormat("EEEE").format(future_prayer_date);
String en_notification_date1 = new SimpleDateFormat("' of ' MMMM").format(future_prayer_date);

String ar_notification_date = new SimpleDateFormat(" EEEE d MMMM ", new Locale("ar")).format(future_prayer_date);
labeconv = "إبتداءا من يوم " + ar_notification_date + "ستتغير اوقات الصلاة   \n";
StringBuilder builder = new StringBuilder();
for(int i =0;i<labeconv.length();i++)
{
    if(Character.isDigit(labeconv.charAt(i)))
    {
        builder.append(arabicChars[(int)(labeconv.charAt(i))-48]);
    }
    else
    {
        builder.append(labeconv.charAt(i));
    }
}
ar_notification_Msg = builder.toString();

en_notification_Msg = "Starting from " + en_notification_date + ", "  + dayStr  + en_notification_date1 + " the following prayer time(s) will change\n";

SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");
//                            en_notification_Msg = "Time change\nTime saving will be in effect as of *Sunday, November 03, 2013*\nAll prayer times will move back by one hour.\nJummah prayer will be at 1:00 PM";
if (fajr_jamma_time_change )
{
    String future_fajr_jamaat_time_mod = DATE_FORMAT.format(future_fajr_jamaat_time);
//                                Date future_fajr_jamaat_time_mod = new SimpleDateFormat("HH:mm").parse("Fajr time: " + future_fajr_jamaat_time);
en_notification_Msg = en_notification_Msg + "Fajr: " + future_fajr_jamaat_time_mod +"    ";
ar_notification_Msg = ar_notification_Msg + "الفجر: " + future_fajr_jamaat_time_mod +"    ";
fajr_jamma_time_change = false;
}


if(Calendar_now.compareTo(nextTransitionCal_min_3)==0 )
{
    String future_zuhr_jamaat_time_mod = DATE_FORMAT.format(zuhr_winter);
    if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time )){future_zuhr_jamaat_time_mod = DATE_FORMAT.format(zuhr_winter); }  
    else{future_zuhr_jamaat_time_mod = DATE_FORMAT.format(zuhr_summer);}
    en_notification_Msg = en_notification_Msg + "Duhr: " + future_zuhr_jamaat_time_mod +"    ";
    ar_notification_Msg = ar_notification_Msg + "الظهر: " + future_zuhr_jamaat_time_mod +"    ";
    zuhr_jamma_time_change = false;
}

if (TimeZone.getTimeZone( timeZone_ID).inDaylightTime( time ) && zuhr_custom_notification_set)
        {
//            String future_zuhr_jamaat_time_mod = DATE_FORMAT.format(future_zuhr_jamaat);
            en_notification_Msg = en_notification_Msg + "Duhr: " + future_zuhr_jamaat +"    ";
            ar_notification_Msg = ar_notification_Msg + "الظهر: " + future_zuhr_jamaat +"    ";
            zuhr_jamma_time_change = false;
            
        }

if (asr_jamma_time_change )
{
    String future_asr_jamaat_time_mod = DATE_FORMAT.format(future_asr_jamaat_time);
//                                Date future_fajr_jamaat_time_mod = new SimpleDateFormat("HH:mm").parse("Fajr time: " + future_fajr_jamaat_time);
en_notification_Msg = en_notification_Msg + "Asr: " + future_asr_jamaat_time_mod +"    ";
ar_notification_Msg = ar_notification_Msg + "العصر: " + future_asr_jamaat_time_mod +"    ";
asr_jamma_time_change = false;
}


if (maghrib_jamma_time_change )
{
    String future_maghrib_jamaat_time_mod = DATE_FORMAT.format(future_maghrib_jamaat_time);
//                                Date future_fajr_jamaat_time_mod = new SimpleDateFormat("HH:mm").parse("Fajr time: " + future_fajr_jamaat_time);
en_notification_Msg = en_notification_Msg + "Maghrib: " + future_maghrib_jamaat_time_mod +"    ";
ar_notification_Msg = ar_notification_Msg + "المغرب: " + future_maghrib_jamaat_time_mod +"    ";
maghrib_jamma_time_change = false;
}



if (isha_jamma_time_change )
{
    String future_isha_jamaat_time_mod = DATE_FORMAT.format(future_isha_jamaat_time);
//                                Date future_fajr_jamaat_time_mod = new SimpleDateFormat("HH:mm").parse("Fajr time: " + future_fajr_jamaat_time);
en_notification_Msg = en_notification_Msg + "Isha: " + future_isha_jamaat_time_mod;
ar_notification_Msg = ar_notification_Msg + "العشاء: " + future_isha_jamaat_time_mod;
isha_jamma_time_change = false;


}


try
{
    c = DBConnect.connect();
    Statement st = (Statement) c.createStatement();
    st.executeUpdate("UPDATE prayertime.notification SET en_message_String='" + en_notification_Msg + "' ORDER BY id DESC LIMIT 1");
    st.executeUpdate("UPDATE prayertime.notification SET ar_message_String= '" + ar_notification_Msg + "' ORDER BY id DESC LIMIT 1");
    c.close();
    ar_notification_Msg_Lines = ar_notification_Msg.split("\\r?\\n");
    en_notification_Msg_Lines = en_notification_Msg.split("\\r?\\n");
}
catch (Exception e){logger.warn("Unexpected error", e);}

System.out.format(ar_notification_Msg);
System.out.format(en_notification_Msg);

notification = false;
//                            athan_Change_Label_visible = true;
notification_Marquee_visible = true;
//                            getFacebook = false;


notification_Msg = ar_notification_Msg_Lines[0] + "\n" + ar_notification_Msg_Lines[1] + "\n\n" + en_notification_Msg_Lines[0] + "\n" + en_notification_Msg_Lines[1];
//                            System.out.println(notification_Msg );

en_Marquee_Notification_string = "Prayer Time Change on " + new SimpleDateFormat("EEEE").format(future_prayer_date) +":   " + en_notification_Msg_Lines[1];
ar_Marquee_Notification_string = "إبتداءا من يوم " + new SimpleDateFormat(" EEEE  ", new Locale("ar")).format(future_prayer_date) + "ستتغير اوقات الصلاة   " + ar_notification_Msg_Lines[1];

en_Marquee_Notification_Text = new Text(en_Marquee_Notification_string);
ar_Marquee_Notification_Text = new Text(ar_Marquee_Notification_string);

//                                System.out.format(en_Marquee_Notification_string);
//                                System.out.format(ar_Marquee_Notification_string);



//                            Twitter twitter = TwitterFactory.getSingleton();
//                            Status status = null;
//                            try {status = twitter.updateStatus(notification_Msg);} 
//                            catch (TwitterException ex) {logger.warn("Unexpected error", e);}
//                            System.out.println("Successfully updated the status to [" + status.getText() + "].");
System.out.println("Notification Sent to Facebook" );
if (facebook_notification_enable)
{
    try
    {
        String pageID = page_ID +"/feed";
        facebookClient.publish(pageID, FacebookType.class, Parameter.with("message", notification_Msg));
    }
    catch (Exception e){logger.warn("Unexpected error", e);}
}

try {push.sendMessage(en_notification_Msg);}
catch (Exception e){{logger.warn("Unexpected error", e);}}
}

////////////////////////////////////////////////////////////////////////////////////

fullMoon = MoonPhaseFinder.findFullMoonFollowing(Calendar.getInstance());
newMoon = MoonPhaseFinder.findNewMoonFollowing(Calendar.getInstance());
//                            System.out.println("The moon is full on " + fullMoon );
//                            System.out.println("The moon is new on " + newMoon );                       
//                            if(newMoon.before(fullMoon)){System.out.println("New moon is before full moon");}
//                            else {System.out.println("Full moon is before new moon" );}
pir_disactive_startup = false;


// ======= Notification for ashura and full moon 5 days earlier, 2 days before fasting period

                            
//TODO Use moonsighting.info to get moon sighting observations

Days d = Days.daysBetween(new DateMidnight(DateTime_now), new DateMidnight(fullMoon));
int days_Between_Now_Fullmoon = d.getDays();
//                            System.out.format("Days left to full moon: %s\n", days_Between_Now_Fullmoon );

fullMoon_plus1 = (Date)fullMoon.clone();
Date ashura = (Date)fullMoon.clone();
DateTimeComparator comparator = DateTimeComparator.getTimeOnlyInstance();
//                                System.out.println(days_Between_Now_Fullmoon);

if (moon_calcs_display &&  dtIslamic.getMonthOfYear()==1 &&  days_Between_Now_Fullmoon ==9 && comparator.compare(fullMoon, maghrib_cal)>0)
{
    //hide hadith label boolean
    getHadith = false;
    //                                getFacebook = false;
    hadith_Label_visible = false;
    //show moon notification label boolean
    moon_hadith_Label_visible = true;
    // 15 - 9 = 6 Ashura = fullMoon_plus1.setTime(fullMoon.getTime() - 4 * 24 * 60 * 60 * 1000);
    try
    {
        c = DBConnect.connect();
        SQL = "select hadith, translated_hadith from hadith WHERE (translated_hadith LIKE '%Ashura%') and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";
    //                                    SQL = "select hadith, translated_hadith from hadith WHERE ID = 1";
    rs = c.createStatement().executeQuery(SQL);
    while (rs.next())
    {
        hadith = rs.getString("hadith");
        translated_hadith = rs.getString("translated_hadith");
    }
    c.close();
    //                                    System.out.println("Ashura arabic hadith length" + hadith.length());
    //                                    System.out.println("Ashura english hadith length" + translated_hadith.length());
    }
    catch (Exception e){logger.warn("Unexpected error", e);}

    ashura.setTime(fullMoon.getTime() - 4 * 24 * 60 * 60 * 1000);
    String Ashura_dow_ar = new SimpleDateFormat("' 'EEEE' '", new Locale("ar")).format(ashura);
    String Ashura_dow_en = new SimpleDateFormat("EEEE").format(ashura);
    String temp_ar_text1 = "نذكركم أيها الأحبة بصيام يوم عاشوراء يوم ";
    String temp_ar_text2 = "إن شاء الله ويوم قبله اوبعده. إن استطعت الصيام فصم و ذكر أحبابك";
    ar_moon_notification = temp_ar_text1 + Ashura_dow_ar + temp_ar_text2;
    en_moon_notification = "A reminder that the 10th of Muharram \"Ashura\" will fall on " + Ashura_dow_en + ", It is recommended to fast either the 9th & 10th of Muharram or the 10th & 11th ";
    facebook_moon_notification_Msg = ar_moon_notification + "\n\n" + en_moon_notification;
    if (facebook_notification_enable)
    {
        try
        {
            String pageID = page_ID +"/feed";
            facebookClient.publish(pageID, FacebookType.class, Parameter.with("message", facebook_moon_notification_Msg));
    //                                        System.out.println("Full Moon Notification Sent to Facebook:" );
    //                                        System.out.println(facebook_moon_notification_Msg);
        }
        catch (FacebookException e){logger.warn("Unexpected error", e);} 
    }
}
else if (moon_calcs_display && dtIslamic.getMonthOfYear()==1 &&  days_Between_Now_Fullmoon ==9 )
{
    //hide hadith label boolean
    getHadith = false;
    //                                getFacebook = false;
    hadith_Label_visible = false;
    //show moon notification label boolean
    moon_hadith_Label_visible = true;


    // 15 - 9 = 6 Ashura = fullMoon_plus1.setTime(fullMoon.getTime() - 5 * 24 * 60 * 60 * 1000);


}


if (moon_calcs_display && dtIslamic.getMonthOfYear()!=9 && days_Between_Now_Fullmoon <= 5 && days_Between_Now_Fullmoon >= 2 || debug)
{
//hide hadith label boolean
getHadith = false;
//                                getFacebook = false;
hadith_Label_visible = false;
//show moon notification label boolean
moon_hadith_Label_visible = true;
//                                try
//                                {
//                                    c = DBConnect.connect();
//                                    SQL = "select hadith, translated_hadith from hadith WHERE day = '15' ORDER BY RAND( ) LIMIT 1";
//                                    rs = c.createStatement().executeQuery(SQL);
//                                    while (rs.next()) 
//                                    {
//                                        ar_full_moon_hadith = rs.getString("hadith");
//                                        en_full_moon_hadith = rs.getString("translated_hadith");
//                                    }
//                                    c.close();
//                                    System.out.format("Full Moon arabic hadith: %s\n", ar_full_moon_hadith );
//                                    System.out.format("Full Moon english hadith: %s\n", en_full_moon_hadith );
//                                }
//                                catch (Exception e){logger.warn("Unexpected error", e);}

//                                ﷺ

ar_full_moon_hadith = " عَنْ جَرِيرِ بْنِ عَبْدِ اللَّهِ عَنْ النَّبِيِّ صَلَّى اللَّهُ عَلَيْهِ وَسَلَّمَ قَالَ : صِيَامُ ثَلاثَةِ أَيَّامٍ مِنْ كُلِّ شَهْرٍ صِيَامُ الدَّهْرِ وَأَيَّامُ الْبِيضِ صَبِيحَةَ ثَلاثَ عَشْرَةَ وَأَرْبَعَ عَشْرَةَ وَخَمْسَ عَشْرَةَ َ ";
en_full_moon_hadith = "The prophet -Pbuh- said \"Fasting three days of every month (13th, 14th & 15th) is equal to Fasting the life time” ";


if ( days_Between_Now_Fullmoon == 5 && comparator.compare(fullMoon, maghrib_cal)<0 )
{
    fullMoon_plus1.setTime(fullMoon.getTime() - 2 * 24 * 60 * 60 * 1000);
    String FullMoon_dow_ar = new SimpleDateFormat("' 'EEEE' '", new Locale("ar")).format(fullMoon_plus1);
    String FullMoon_dow_en = new SimpleDateFormat("EEEE").format(fullMoon_plus1);
    String temp_ar_text1 = "نذكركم و أنفسنا بفضل صيام الايام البيض من كل شهر التي تبدأ يوم";
    String temp_ar_text2 = "إن استطعت الصيام فصم و ذكر أحبابك. يرجى ملاحظة أن هذا يقوم على حسابات التقويم";
    ar_moon_notification = temp_ar_text1 + FullMoon_dow_ar + temp_ar_text2;
    en_moon_notification = "We would like to remind you that this month's \"White days\" will start this " + FullMoon_dow_en + ", it is recommended to fast these days. (This is based on calendar calculations)";
    facebook_moon_notification_Msg = ar_moon_notification + "\n\n" + en_moon_notification;
//                                    try
//                                    {
//                                        String pageID = page_ID +"/feed";
//                                        facebookClient.publish(pageID, FacebookType.class, Parameter.with("message", facebook_moon_notification_Msg));
//                                    }
//                                    catch (FacebookException e){logger.warn("Unexpected error", e);}                           
//                                    System.out.println("Full Moon Notification Sent to Facebook:" );
//                                    System.out.println(facebook_moon_notification_Msg);
}

else if ( days_Between_Now_Fullmoon == 5 )
{
    if (comparator.compare(fullMoon, maghrib_cal)>0)
    {
        fullMoon_plus1.setTime(fullMoon.getTime() - 1 * 24 * 60 * 60 * 1000);
        String FullMoon_dow_ar = new SimpleDateFormat("' 'EEEE' '", new Locale("ar")).format(fullMoon_plus1);
        String FullMoon_dow_en = new SimpleDateFormat("EEEE").format(fullMoon_plus1);
        String temp_ar_text1 = "نذكركم و أنفسنا بفضل صيام الايام البيض من كل شهر التي تبدأ يوم";
        String temp_ar_text2 = "إن استطعت الصيام فصم و ذكر أحبابك. يرجى ملاحظة أن هذا يقوم على حسابات التقويم";
        ar_moon_notification = temp_ar_text1 + FullMoon_dow_ar + temp_ar_text2;
        en_moon_notification = "We would like to remind you that this month's \"White days\" will start this " + FullMoon_dow_en + ", it is recommended to fast these days. (This is based on calendar calculations)";
        facebook_moon_notification_Msg = ar_moon_notification + "\n\n" + en_moon_notification;
        if (facebook_notification_enable)
        {
            try
            {
                String pageID = page_ID +"/feed";
                facebookClient.publish(pageID, FacebookType.class, Parameter.with("message", facebook_moon_notification_Msg));
//                                                System.out.println("Full Moon Notification Sent to Facebook:" );
//                                                System.out.println(facebook_moon_notification_Msg);
            }
            catch (FacebookException e){logger.warn("Unexpected error", e);}

        }

    }

    else
    {
        fullMoon_plus1.setTime(fullMoon.getTime() - 2 * 24 * 60 * 60 * 1000);
        String FullMoon_dow_ar = new SimpleDateFormat("' 'EEEE' '", new Locale("ar")).format(fullMoon_plus1);
        String FullMoon_dow_en = new SimpleDateFormat("EEEE").format(fullMoon_plus1);
        String temp_ar_text1 = "نذكركم و أنفسنا بفضل صيام الايام البيض من كل شهر التي تبدأ يوم";
        String temp_ar_text2 = "إن استطعت الصيام فصم و ذكر أحبابك. يرجى ملاحظة أن هذا يقوم على حسابات التقويم";
        ar_moon_notification = temp_ar_text1 + FullMoon_dow_ar + temp_ar_text2;
        en_moon_notification = "We would like to remind you that this month's \"White days\" will start this " + FullMoon_dow_en + ", it is recommended to fast these days. (This is based on calendar calculations)";
        facebook_moon_notification_Msg = ar_moon_notification + "\n\n" + en_moon_notification;                          
//                                        System.out.println("Full Moon Notification:" );
//                                        System.out.println(facebook_moon_notification_Msg);
    }
}

else if ( days_Between_Now_Fullmoon == 3 )
{
    if (comparator.compare(fullMoon, maghrib_cal)>0)
    {
        fullMoon_plus1.setTime(fullMoon.getTime() - 1 * 24 * 60 * 60 * 1000);
        String FullMoon_dow_ar = new SimpleDateFormat("' 'EEEE' '", new Locale("ar")).format(fullMoon_plus1);
        String FullMoon_dow_en = new SimpleDateFormat("EEEE").format(fullMoon_plus1);
        String temp_ar_text1 = "نذكركم و أنفسنا بفضل صيام الايام البيض من كل شهر التي تبدأ يوم";
        String temp_ar_text2 = "إن استطعت الصيام فصم و ذكر أحبابك. يرجى ملاحظة أن هذا يقوم على حسابات التقويم";
        ar_moon_notification = temp_ar_text1 + FullMoon_dow_ar + temp_ar_text2;
        en_moon_notification = "We would like to remind you that this month's \"White days\" will start this " + FullMoon_dow_en + ", it is recommended to fast these days. (This is based on calendar calculations)";
        facebook_moon_notification_Msg = ar_moon_notification + "\n\n" + en_moon_notification;
    }

    else
    {
        String temp_ar_text1 = "نذكركم و أنفسنا بفضل صيام الايام البيض من كل شهر التي تبدأ غدا ";
        String temp_ar_text2 = "إن استطعت الصيام فصم و ذكر أحبابك. يرجى ملاحظة أن هذا يقوم على حسابات التقويم";
        ar_moon_notification = temp_ar_text1 +  temp_ar_text2;
        en_moon_notification = "We would like to remind you that this month's \"White days\" will start tomorrow, it is recommended to fast these days. (This is based on calendar calculations)";
        facebook_moon_notification_Msg = ar_moon_notification + "\n\n" + en_moon_notification;
        if (facebook_notification_enable)
        {
            try
            {
                String pageID = page_ID +"/feed";
                facebookClient.publish(pageID, FacebookType.class, Parameter.with("message", facebook_moon_notification_Msg));
//                                                System.out.println("Full Moon Notification Sent to Facebook:" );
//                                                System.out.println(facebook_moon_notification_Msg);
            }
            catch (FacebookException e){logger.warn("Unexpected error", e);}
        }

    }
}

else if ( days_Between_Now_Fullmoon == 2 && comparator.compare(fullMoon, maghrib_cal)>0 || debug)
{
    String temp_ar_text1 = "نذكركم و أنفسنا بفضل صيام الايام البيض من كل شهر التي تبدأ غدا ";
    String temp_ar_text2 = "إن استطعت الصيام فصم و ذكر أحبابك. يرجى ملاحظة أن هذا يقوم على حسابات التقويم";
    ar_moon_notification = temp_ar_text1 +  temp_ar_text2;
//                                        System.out.println(ar_moon_notification);
en_moon_notification = "We would like to remind you that this month's \"White days\" will start tomorrow, it is recommended to fast these days. (This is based on calendar calculations)";
//                                        System.out.println(en_moon_notification);
facebook_moon_notification_Msg = ar_moon_notification + "\n\n" + en_moon_notification;
if (facebook_notification_enable)
{
    try
    {
        String pageID = page_ID +"/feed";
        facebookClient.publish(pageID, FacebookType.class, Parameter.with("message", facebook_moon_notification_Msg));
//                                                System.out.println("Full Moon Notification Sent to Facebook:" );
//                                                System.out.println(facebook_moon_notification_Msg);
    }
    catch (FacebookException e){logger.warn("Unexpected error", e);}
}

}
}
else
{
    getHadith = true;
    moon_hadith_Label_visible = false;
    hadith_Label_visible = true;
//                                    System.out.println("moon else" );
}
    

//    else if( days_Between_Now_Fullmoon > 5 || days_Between_Now_Fullmoon < 2 || dtIslamic.getMonthOfYear()!=1)
//    {
//        getHadith = true; 
//    //                                getFacebook = true;
//    //hide moon notification label boolean
//    moon_hadith_Label_visible = false;
//    //show hadith label boolean
//    hadith_Label_visible = true;
//    }
  

}



if (isStarting){isStarting = false;}

// Get Facebook Latest Post =================================================================================
if (facebook_Receive)
{
//                            getFacebook = false;
    facebook_Text_Post = false;
    facebook_Picture_Post = false;
    facebook_post = "";
//                            facebook_Post_Url = "";
facebook_Fan_Count = "";
Calendar facebook_created_time_calendar = null;
Calendar facebook_photo_created_time_calendar = null;
Calendar facebook_check_post_date = Calendar.getInstance();
facebook_check_post_date.add(Calendar.DAY_OF_MONTH, -6);
long facebook_check_post_Unix_Time = facebook_check_post_date.getTimeInMillis() / 1000;
out.println(facebook_check_post_Unix_Time);
String query = "SELECT message,timeline_visibility, created_time   FROM stream WHERE source_id = " + page_ID + " AND message AND strlen(attachment.fb_object_type) < 1 AND type != 56 AND type = 46  AND strpos(message, \"prayer time(s)\") < 0 AND strpos(message, \"White days\") < 0 AND strpos(message, \"Hadith of the Day:\") < 0 AND created_time > " + facebook_check_post_Unix_Time + " LIMIT 1";
//                            String query = "{\"messages\":\"SELECT message,timeline_visibility, created_time   FROM stream WHERE source_id = " + page_ID + " AND message AND strlen(attachment.fb_object_type) < 1 AND type != 56 AND type = 46  AND strpos(message, \'prayer time(s)\') < 0 AND strpos(message, \'White days\') < 0 AND strpos(message, \'Hadith of the Day:\') < 0 AND created_time > " + facebook_check_post_Unix_Time + " LIMIT 1\" ,  \"count\": \"SELECT fan_count FROM page WHERE page_id = " + page_ID + "\"}";
//                            out.println(query);
try
{
    List<JsonObject> queryResults = facebookClient.executeFqlQuery(query, JsonObject.class);
    
    if(!queryResults.isEmpty())
    {
        JsonObject facebookPost_J = queryResults.get(0);
        facebook_post = facebookPost_J.getString("message");
        
//                                    facebook_post = "Asalamualaikum,\n" + "We have been given a large printer/copier for administration at Daar Ibn\n Abbas. Is there any brothers available to pick it up from Lakemba? ";

String[] lines = facebook_post.split("\r\n|\r|\n");

if(null != facebook_post && !"".equals(facebook_post))
{
    if(facebook_post.contains("\n\n"))
    {
        out.println("'/n/n' detected");
        facebook_post =facebook_post.replace("\n\n", "\n");
        out.println(facebook_post);
    }
    facebook_created_time_calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone_ID));
    facebook_created_time_calendar.setTimeInMillis(queryResults.get(0).getLong("created_time")* 1000);
//                                        out.print("Comment posted on:"); out.println(facebook_created_time_calendar.getTime());
if(facebook_post.contains("tonight") || facebook_post.contains("today") && Days.daysBetween(new DateMidnight(DateTime_now), new DateMidnight(facebook_created_time_calendar)).getDays() != 0)
{
    out.println("Facebook post contains either  the word 'today' or 'tonight' and has not been posted today");
    facebook_post = "";
    facebook_Label_visible = false;
}

else if (facebook_post.length() > 390 || lines.length > 6)
{
//                                            System.out.println("Facebook post is too large, it will not be posted");
//                                            System.out.println("Facebook lines: " +lines.length);
//                                            System.out.println("Facebook string length: " + facebook_post.length());
    facebook_post = "";
    facebook_Label_visible = false;
} 

else
{
    facebook_Text_Post = true;
    facebook_Label_visible = true;
    facebook_Label_visible_set_once = true;
}
}
    }
    else{out.println("Facebook post is empty");}
}
catch (FacebookException e){logger.warn("Unexpected error", e);}
catch (Exception e){logger.warn("Unexpected error", e);}

query = "SELECT fan_count FROM page WHERE page_id = " + page_ID ;
try
{
    List<JsonObject> queryResults = facebookClient.executeFqlQuery(query, JsonObject.class);
    facebook_Fan_Count = queryResults.get(0).getString("fan_count");
    out.println("Page Likes: " + facebook_Fan_Count);
    
}
catch (FacebookException e){logger.warn("Unexpected error", e);}
catch (Exception e){logger.warn("Unexpected error", e);}

query = "SELECT attachment.media.photo.images.src, created_time   FROM stream WHERE source_id = " + page_ID + "  AND type = 247 AND created_time > " + facebook_check_post_Unix_Time ; //+ " LIMIT 1"
try
{
    List<JsonObject> queryResults = facebookClient.executeFqlQuery(query, JsonObject.class);
    if(!queryResults.isEmpty())
    {
        if(null != facebook_Post_Url && !"".equals(facebook_Post_Url)){ old_facebook_Post_Url = new String(facebook_Post_Url);}
//                                    out.println(old_facebook_Post_Url);

try
{facebook_Post_Url = queryResults.get(0).getJsonObject("attachment").getJsonArray("media").getJsonObject(0).getJsonObject("photo").getJsonArray("images").getJsonObject(1).getString("src");}
catch (Exception e){logger.warn("facebook post url exception", e);}

out.println(facebook_Post_Url);

facebook_photo_created_time_calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone_ID));
facebook_photo_created_time_calendar.setTimeInMillis(queryResults.get(0).getLong("created_time")* 1000);
out.print("Comment posted on:"); out.println(facebook_photo_created_time_calendar.getTime());



if(null != facebook_Post_Url && !"".equals(facebook_Post_Url) )
{
    if(null != old_facebook_Post_Url && !"".equals(old_facebook_Post_Url))
    {
        if(facebook_Post_Url.equals(old_facebook_Post_Url))
        {out.print("Facebook photo post has not changed from previous fetch, nothing has been set");}
        
        if(!facebook_Post_Url.equals(old_facebook_Post_Url))
        {
            facebook_Picture_Post = true;
            facebook_Label_visible = true;
            facebook_Label_visible_set_once = true;
        }
    }
    
    else
    {
        facebook_Picture_Post = true;
        facebook_Label_visible = true;
        facebook_Label_visible_set_once = true;
    }
}
    }
    else {out.println("facebook photo post query empty");
    
    if (facebook_image_debug)
    {
//                                    facebook_Post_Url = "https://scontent.xx.fbcdn.net/hphotos-xfl1/v/t1.0-9/s720x720/12741993_1144527992248765_594594912175483215_n.png?oh=e69113bda140e23531dd65aa1b5a4efc&oe=576C1587";
        facebook_Post_Url = "https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-xpf1/v/t1.0-9/11429818_1016762725025293_7919066187654522176_n.png?oh=3b38ae20db78e48fc1772d45a12a60ef&oe=56E2542D&__gda__=1457667602_8ded0d24fc8943fab85f024b99fe8ac5";
        facebook_Picture_Post = true;
        facebook_Label_visible = true;
        facebook_Label_visible_set_once = true;
    }
    
    
    }
    
}
catch (FacebookException e){logger.warn("Unexpected error", e);}
catch (Exception e){logger.warn("Unexpected error", e);}

//compare text and picture post dates, if facebook_Picture_Post && facebook_Text_Post are true, and dates are not null
// which ever was posted last, clear facebook_post = ""; or facebook_Post_Url = "";
if(facebook_Picture_Post && facebook_Text_Post)
{
    if (facebook_photo_created_time_calendar.before(facebook_created_time_calendar))
    {
        facebook_Post_Url = "";
        
    }
    
    else
    {
        facebook_post = "";
        
    }
}
}







// Get Daily Hadith =================================================================================
if (getHadith)
{
    
    getHadith = false;
    
    if (!vertical)
    {
        max_ar_hadith_len =   300          ;
        max_en_hadith_len =   277          ;
//                                System.out.println("Screen configured to Horizontal position");

    }
    try
    {
        c = DBConnect.connect();
        
        if (athan_Change_Label_visible)
        {
            max_en_hadith_len = 277;
            max_ar_hadith_len = 200;
//                                    System.out.println("****Short hadith due to prayer time change label******");



        }
        
//                                System.out.println("max arabic hadith length" + max_ar_hadith_len);

if (dtIslamic.getMonthOfYear()==9 && dtIslamic.getDayOfMonth()<19){SQL ="select hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)  from hadith WHERE topic = 'fasting' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}
//                                if (dtIslamic.getMonthOfYear()==9){SQL ="select hadith, translated_hadith from hadith WHERE ID = 2872";}
else if (dtIslamic.getMonthOfYear()==9 && dtIslamic.getDayOfMonth()>19){SQL ="select hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)  from hadith WHERE topic = 'Virtues of the Night of Qadr' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}
else if (dtIslamic.getMonthOfYear()==9 && dtIslamic.getDayOfMonth()>28){SQL ="select hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)  from hadith WHERE translated_hadith LIKE '%fitr %' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}
else if (dtIslamic.getMonthOfYear()==10 && dtIslamic.getDayOfMonth()==1){SQL ="select hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)  from hadith WHERE translated_hadith LIKE '%fitr %' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}
//SELECT * FROM hadith WHERE translated_hadith LIKE '%fitr %'
else if(dtIslamic.getMonthOfYear()==12){SQL ="select hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)  from hadith WHERE topic = 'Hajj (Pilgrimage)' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}
else if (dayofweek_int == 6){SQL = "select hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)  from hadith WHERE day = '5' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}

else if (dtIslamic.getMonthOfYear()==1 && dtIslamic.getDayOfMonth()>7 && dtIslamic.getDayOfMonth()<12 ){SQL = "select hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)  from hadith WHERE (translated_hadith LIKE '%Ashura%') and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}


else
{
    SQL = "select hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)   from hadith WHERE day = '0' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";
//                                    SQL = "select * from hadith where  CHAR_LENGTH(translated_hadith)>527"; // the bigest Hadith
}


//                                SQL = "select * from hadith WHERE CHAR_LENGTH(hadith)>400  ORDER BY RAND( ) LIMIT 1";
//                                SQL = "select * from hadith WHERE ID =1486  ORDER BY RAND( ) LIMIT 1";
//                                    SQL = "select  hadith, CHAR_LENGTH(hadith), translated_hadith,CHAR_LENGTH(translated_hadith)  from hadith WHERE ID =1078  ORDER BY RAND( ) LIMIT 1";
//                                athan_Change_Label_visible = false;

String ara_length = null;
String en_length = null;

rs = c.createStatement().executeQuery(SQL);
while (rs.next())
{
    hadith = rs.getString("hadith");
    translated_hadith = rs.getString("translated_hadith");
    ara_length = rs.getString("CHAR_LENGTH(hadith)");
    en_length = rs.getString("CHAR_LENGTH(translated_hadith)");
    
    
}
c.close();
                                System.out.println("arabic hadith length" + hadith.length());
                                System.out.println(" english hadith length" + translated_hadith.length());
                                System.out.println("mysql arabic hadith length" + ara_length);
                                System.out.println("mysql english hadith length" + en_length);

// 528 length should be the max allowed for the hadith in english, generally arabic hadith  is smaller than english translation
facebook_hadith = "Hadith of the Day:\n\n"+ hadith +"\n\n" + translated_hadith;

// check if a notification has already been sent, to avoid flooding users with notifications, i.e during system restarts
c = DBConnect.connect();
SQL = "Select * from facebook_hadith_notification where id = (select max(id) from facebook_hadith_notification)";
rs = c.createStatement().executeQuery(SQL);
while (rs.next())
{
    id =                rs.getInt("id");
    hadith_notification_Date = rs.getDate("notification_date");
}
c.close();
hadith_notification_Date_cal = Calendar.getInstance();
hadith_notification_Date_cal.setTime(hadith_notification_Date);
hadith_notification_Date_cal.set(Calendar.MILLISECOND, 0);
hadith_notification_Date_cal.set(Calendar.SECOND, 0);

//                                System.out.println(hadith_notification_Date_cal.getTime());
//                                System.out.println(Calendar_now.getTime());
if (Calendar_now.compareTo(hadith_notification_Date_cal) == 0 )
{
//                                    System.out.println("hadith has already been posted today to Facebook");
}

if (Calendar_now.compareTo(hadith_notification_Date_cal) != 0 )
{
    try
    {
        
        if (facebook_notification_enable)
        {
            try
            {
                String pageID = page_ID +"/feed";
                facebookClient.publish(pageID, FacebookType.class, Parameter.with("message", facebook_hadith));
                c = DBConnect.connect();
                PreparedStatement ps = c.prepareStatement("INSERT INTO prayertime.facebook_hadith_notification (notification_date) VALUE (?)");
                java.sql.Timestamp mysqldate = new java.sql.Timestamp(new java.util.Date().getTime());
                ps.setTimestamp(1, mysqldate);
                ps.executeUpdate();
                c.close();
                System.out.println("hadith posted to Facebook: \n" + facebook_hadith );
            }
            catch (FacebookException e){logger.warn("Unexpected error", e);}
        }
        
        
    }
    catch (FacebookException e){logger.warn("Unexpected error", e);}
    catch (Exception e){logger.warn("Unexpected error", e);}
}
    }
    catch (Exception e){logger.warn("Unexpected error", e);}
    
}
        }
        
        catch(SQLException e)
        {
            System.out.println("Error on Database connection");
            logger.warn("Unexpected error", e);
        }
        catch (ParseException e){logger.warn("Unexpected error", e);}
        catch (Exception e){logger.warn("Unexpected error", e);}
        
        
    }
}, 0, 3600000);
//        }, 0, 120000);

// Timer to traslate labels from arabic to english on the screen====================================================

//        translate_lastTimerCall = System.nanoTime();
translate_timer = new AnimationTimer() {
    @Override public void handle(long now) {
        if (now > translate_lastTimerCall + 40000_000_000l) //40000_000_000l
        {
            try {update_labels();}
            catch (Exception e) {logger.warn("Unexpected error", e);}
            translate_lastTimerCall = now;
        }
    }
};

// Timer to update clock===============================================================================================

//        translate_lastTimerCall = System.nanoTime();
clock_update_timer = new AnimationTimer() {
    @Override public void handle(long now) {
        if (now > clock_update_lastTimerCall + 1000_000_000l)
        {
            try {play_athan();}
            catch (Exception e) {logger.warn("Unexpected error", e);}
            clock_update_lastTimerCall = now;
        }
    }
};
// ============================================================================================


hour_Label.textProperty().bind(hour);
minute_Label.textProperty().bind(minute);
second_Label.textProperty().bind(second);
date_Label.textProperty().bind(date);
//        day_Label.textProperty().bind(day_date);
//        new Thread(() -> 
//        {
//
//             for (;;)
//             {
//                try
//                { 
//                    final SimpleDateFormat simpledate_hour = new SimpleDateFormat("h");
//                    final SimpleDateFormat simpledate_minute = new SimpleDateFormat("mm");
//                    final SimpleDateFormat simpledate_second = new SimpleDateFormat("s");
//                    Platform.runLater(new Runnable() {
//                        @Override public void run() 
//                        {
//                                hour.set(simpledate_hour.format(new Date()));
//                                minute.set(simpledate_minute.format(new Date()));
//                                second.set(simpledate_second.format(new Date()));
// 
//                        }
//                    });
//                    Thread.sleep(200);
//                            
//                }
//                catch (Exception e){logger.warn("Unexpected error", e); Thread.currentThread().interrupt(); Platform.exit(); System.exit(0);}
//                        
//             }
//
//        }).start();



Timeline clock = new Timeline(
        new KeyFrame(Duration.seconds(0), evt -> {
            LocalTime now = LocalTime.now();
            Calendar_now_hourpane = Calendar.getInstance();
            hour.set(new SimpleDateFormat("h").format(new Date()));
            minute.set(String.format("%02d", now.getMinute()));
            second.set(String.format("%d", now.getSecond()));
            date.set(new SimpleDateFormat("EEEE, MMM d").format(Calendar_now_hourpane.getTime()));
//        day_date.set(new SimpleDateFormat("EEEE").format(Calendar_now_hourpane.getTime()));
        
        }),
        new KeyFrame(Duration.seconds(1))
);
clock.setCycleCount(Animation.INDEFINITE);
clock.play();

if(weather_enabled)
    
{
    new Thread(() ->
    {
        
                
        String url = "http://api.wunderground.com/api/b28d047ca410515a/conditions/q/-33.912,151.013.json";
        String url2 = "http://api.wunderground.com/api/b28d047ca410515a/forecast/q/-33.912,151.013.json";
        InputStream is = null; 
        
        System.out.println(" ... Weather Forecast Starting.....");
        
        for (;;)
        {
            try 
            {
                
                
                        if ( startup)
                        {
                           
                            is = new URL(url).openStream();
                            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                            String jsonText = readAll(rd);
                            JsonElement je = new JsonParser().parse(jsonText);
                            is = new URL(url2).openStream();
                            BufferedReader rd2 = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                            String jsonText2 = readAll(rd2);
                            JsonElement je2 = new JsonParser().parse(jsonText2);

                            System.out.println("Current Temperature:" + getAtPath(je, "current_observation/temp_c").getAsString() );
//                            weather_icon_url = getAtPath(je, "current_observation/icon_url").getAsString();
                            System.out.println("startup is true");
                            Weather_icon = getAtPath(je, "current_observation/icon").getAsString();
                            System.out.println("Weather Icon:" + Weather_icon );
//                            System.out.println("max Temperature:" + getAtPath(je2, "forecast/simpleforecast/forecastday/high/celsius").getAsString() );
//                            System.out.println("min Temperature:" + getAtPath(je2, "forecast/simpleforecast/forecastday/low/celsius").getAsString() );

                            Platform.runLater(new Runnable() 
                            {
                                @Override public void run()
                                {
                                    if (weather_retrieve_fault){Weatherpane.setVisible(true);}
                                    Weather_Label1.setText(getAtPath(je, "current_observation/temp_c").getAsString() +"°C");
                                    Weather_Label2.setText(getAtPath(je2, "forecast/simpleforecast/forecastday/low/celsius").getAsString() +"°" + "/" + getAtPath(je2, "forecast/simpleforecast/forecastday/high/celsius").getAsString()+"°");                              
                                    
                                    if (Weather_icon.equals("mostlycloudy"))
                                    {
                                        
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal) && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/mostlycloudy.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/mostlycloudy_night.png";
                                    }
                                            
                                    else if (Weather_icon.equals("partlycloudy"))
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal)  && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/partlycloudy.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/partlycloudy_night.png";
                                    }
                                    
                                    else if (Weather_icon.equals("cloudy")){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/cloudy5.png";}
                                    
                                      
                                    else if (Weather_icon.equals("mostlysunny"))
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal)  && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/mostlysunny.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/mostlysunny_night.png";
                                    }
                                    
                                    else if (Weather_icon.equals("partlysunny"))
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal) && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/partlysunny.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/partlysunny_night.png";
                                    }
                                    
                                    else if (Weather_icon.equals("sunny") || Weather_icon.equals("clear"))
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal)  && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/sunny.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/sunny_night.png";
                                    }
                                    
                                    else if (Weather_icon.equals("rain") ){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/rain.png";}
                                    
                                    else if (Weather_icon.equals("sleet") ){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/sleet.png";}
                                    
                                    else if (Weather_icon.equals("snow") ){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/snow4.png";}
                                    
                                    else if (Weather_icon.equals("tstorms") ){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/tstorm3.png";}
                                    
                                    else if (Weather_icon.equals("fog") || Weather_icon.equals("hazy") )
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal)  && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/fog.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/fog_night.png";
                                    }
                                    

                                    else{Weather_Image_Label.setVisible(false); weather_image_wrong = true;}
                                    
                                    
                                    
                                    ImageView weather_img = new ImageView(new Image(getClass().getResourceAsStream(weather_image_string)));  
                                    Weather_Image_Label.setGraphic(weather_img);
                                    
                                    if (orientation.equals("horizontal"))
                                    {
                                        weather_img.setFitWidth(50);
                                        weather_img.setFitHeight(50);
                                    }
                                    
                                    else if (orientation.equals("camera_mode") )
                                    {
                                        weather_img.setFitWidth(35);
                                        weather_img.setFitHeight(35);
                                    }
                                    
                                    else 
                                    {
                                        weather_img.setFitWidth(160);
                                        weather_img.setFitHeight(160);
                                    }
                                    if(!weather_image_wrong){Weather_Image_Label.setVisible(true); }
                                }
                            });
                        }
                    
                        Thread.sleep(300000);
                        LocalTime now = LocalTime.now();
                        Calendar_now = Calendar.getInstance();
                        is = new URL(url).openStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                        String jsonText = readAll(rd);
                        JsonElement je = new JsonParser().parse(jsonText);
                        is = new URL(url2).openStream();
                        BufferedReader rd2 = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                        String jsonText2 = readAll(rd2);
                        JsonElement je2 = new JsonParser().parse(jsonText2);

                        System.out.println("Current Temperature:" + getAtPath(je, "current_observation/temp_c").getAsString() );
//                        weather_icon_url = getAtPath(je, "current_observation/icon_url").getAsString();
//                        System.out.println("Weather URL:" + weather_icon_url);
                        Weather_icon = getAtPath(je, "current_observation/icon").getAsString();
                        System.out.println("Weather Icon:" + Weather_icon );
//                        System.out.println("max Temperature:" + getAtPath(je2, "forecast/simpleforecast/forecastday/high/celsius").getAsString() );
//                        System.out.println("min Temperature:" + getAtPath(je2, "forecast/simpleforecast/forecastday/low/celsius").getAsString() );

                        Platform.runLater(new Runnable() 
                        {
                            @Override public void run()
                            {
                                if (weather_retrieve_fault){Weatherpane.setVisible(true);}
                                    Weather_Label1.setText(getAtPath(je, "current_observation/temp_c").getAsString() +"°C");
                                    Weather_Label2.setText(getAtPath(je2, "forecast/simpleforecast/forecastday/low/celsius").getAsString() +"°" + "/" + getAtPath(je2, "forecast/simpleforecast/forecastday/high/celsius").getAsString()+"°");                              
                                    
                                    if (Weather_icon.equals("mostlycloudy"))
                                    {
                                        
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal) && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/mostlycloudy.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/mostlycloudy_night.png";
                                    }
                                            
                                    else if (Weather_icon.equals("partlycloudy"))
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal) && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/partlycloudy.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/partlycloudy_night.png";
                                    }
                                    
                                    else if (Weather_icon.equals("cloudy")){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/cloudy5.png";}
                                    
                                      
                                    else if (Weather_icon.equals("mostlysunny"))
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal) && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/mostlysunny.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/mostlysunny_night.png";
                                    }
                                    
                                    else if (Weather_icon.equals("partlysunny"))
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal) && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/partlysunny.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/partlysunny_night.png";
                                    }
                                    
                                    else if (Weather_icon.equals("sunny") || Weather_icon.equals("clear"))
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal) && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/sunny.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/sunny_night.png";
                                    }
                                    
                                    else if (Weather_icon.equals("rain") ){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/rain.png";}
                                    
                                    else if (Weather_icon.equals("sleet") ){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/sleet.png";}
                                    
                                    else if (Weather_icon.equals("snow") ){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/snow4.png";}
                                    
                                    else if (Weather_icon.equals("tstorms") ){weather_image_wrong = false; weather_image_string = "/Images/Weather/set1/tstorm3.png";}
                                    
                                    else if (Weather_icon.equals("fog") || Weather_icon.equals("hazy") )
                                    {
                                        weather_image_wrong = false;
                                        if(Calendar_now.before(maghrib_plus15_cal) && Calendar_now.after(sunrise_cal))
                                            weather_image_string = "/Images/Weather/set1/fog.png";
                                        else
                                            weather_image_string = "/Images/Weather/set1/fog_night.png";
                                    }
                                    

                                    else{Weather_Image_Label.setVisible(false); weather_image_wrong = true;}
                                    
                                    
                                    
                                    ImageView weather_img = new ImageView(new Image(getClass().getResourceAsStream(weather_image_string)));  
                                    Weather_Image_Label.setGraphic(weather_img);
                                    if (orientation.equals("horizontal"))
                                    {
                                        weather_img.setFitWidth(50);
                                        weather_img.setFitHeight(50);
                                    }
                                    
                                    else if (orientation.equals("camera_mode") )
                                    {
                                        weather_img.setFitWidth(35);
                                        weather_img.setFitHeight(35);
                                    }
                                    
                                    else 
                                    {
                                        weather_img.setFitWidth(160);
                                        weather_img.setFitHeight(160);
                                    }
                                    if(!weather_image_wrong){Weather_Image_Label.setVisible(true); }
                                        
                            }
                        });

//                }
            }
            
            catch (Exception ex)
            {            
                java.util.logging.Logger.getLogger(JavaFXApplication4.class.getName()).log(Level.SEVERE, null, ex);
                Platform.runLater(new Runnable() 
                    {
                        @Override public void run()
                        {
                            Weatherpane.setVisible(false);
                            weather_retrieve_fault = true;
                        }
                    });
            }

            finally 
            {
                try 
                {
                    if (is != null)
                        is.close();
                } 
                catch (IOException e) {e.printStackTrace(); }
            }   
                    
// =======>>>           #############  LOCATION OF Thread.currentThread().interrupt();

        }
        
    }).start();
}
    

// Sonar sensor thread to turn Camera ===============================================================
if (platform.equals("pi") && sonar_active)
{
    new Thread(() ->
    {
        // create an instance of the serial communications class
        final Serial serial = SerialFactory.createInstance();
//                System.out.println(" ... Sonar Detection Starting.....");
// create and register the serial data listener
//serial.addListener(new SerialDataListener()
//{
//    @Override
//    public void dataReceived(SerialDataEvent event)
//    {
//        try
//        {
//            String newString = event.getData().substring(1,5);
//            sonar_distance = Integer.parseInt(newString);
//        }
//        catch(Exception ex) {System.out.println("=====Sonar string out of bound====");}
//        //                System.out.print(sonar_distance);
//        
//    }
//});
//
//// open the default serial port provided on the GPIO header
////                System.out.println(" ... Openning Serial connection");
//try {serial.open(Serial.DEFAULT_COM_PORT, 9600);}
//catch(SerialPortException ex)
//{
////                         System.out.println(" ==>> SERIAL SETUP FAILED : " + ex.getMessage());
//    try {push.sendMessage(temp_msg);} catch (IOException e){e.printStackTrace();}
//    Thread.currentThread().interrupt();
//}
//                System.out.println(" ... Serial connection Open");
System.out.println(" ... Sonar Detection Starting.....");
for (;;)
{
    try
    {
        Thread.sleep(650);
        if(sonar_active)
        {
            if (sonar_distance>sonar_active_distance) 
            {
                //                        System.out.println(sonar_distance);
                sonar_lastTimerCall = System.nanoTime();
                if(radar_displayed && !manual_radar_display ) 
                {
                    radar_displayed = false;
                    Platform.runLater(new Runnable() 
                    {
                        @Override public void run()
                        {
                            Mainpane.getChildren().remove(thermoMeter);
                            radar_gauge_anninmation.stop();
                        }
                    });
                }
                
                
                
            }
            
            if (sonar_distance<sonar_active_distance)
            {
                //                        System.out.println(sonar_distance);
                sensor1_lastTimerCall = System.nanoTime();
                
                if(!radar_displayed)
                {
                    radar_displayed = true;
                    Platform.runLater(new Runnable()
                    {
                        @Override public void run()
                        {
                            
                            //                                        thermoMeter.setTranslateX(300);
                            //                                        thermoMeter.setTranslateY(100);
                            Mainpane.add(thermoMeter, 0, 27,2,2);
                            radar_gauge_anninmation.start();
                        }
                    });
                }
                
            }
            
            if (System.nanoTime() > sonar_lastTimerCall + delay_switch_to_cam && sonar_distance<sonar_active_distance && !camera && !friday_jamaat_cam_activated)
            {
                
                System.out.println("Switching to Camera...");
//                                ProcessBuilder processBuilder_camera_on = new ProcessBuilder("bash", "-c", "raspistill -vf -p '25,12,670,480'  -t 5400000 -tl 200000 -w 640 -h 400 -o cam2.jpg");
                try {Process process = processBuilder_camera_on.start(); }
                catch (IOException e) {logger.warn("Unexpected error", e);}
                sensor1_lastTimerCall = System.nanoTime();
                camera = true;
                prayer_In_Progress = true;
                if(local_HDMI_control)
                {
                    System.out.println("Turning Tv OFF");
                //                                    ProcessBuilder processBuilder2 = new ProcessBuilder("bash", "-c", "echo \"standby 0000\" | cec-client -d 1 -s \"standby 0\" RPI");
                hdmiOn = false;
                try {Process process2 = processBuilder2.start();}
                catch (IOException e) {logger.warn("Unexpected error", e);}
                }

                Calendar cal = Calendar.getInstance();
                int hour_Now_int = cal.get(Calendar.HOUR_OF_DAY);
                int hourbefore_fajr_int = fajr_cal.get(Calendar.HOUR_OF_DAY) -1;
                hourbefore_fajr_int = hourbefore_fajr_int -1;
                //                                System.out.println("hour now is" + hour_Now_int);
                //                                System.out.println("fajr hour -1 hour is" + hourbefore_fajr_int);


                //                                System.out.println("prayer detected during normal hours");

                if (fajr_prayer_In_Progress_notification && cal.after(fajr_jamaat_cal) && cal.before(fajr_jamaat_update_cal))
                {
                    fajr_prayer_In_Progress_notification = false;
                    try {push.sendMessage("Fajr Jamaa at Daar Ibn Abass has just started"); System.out.println("Prayer in progress notification sent");} catch (IOException e){e.printStackTrace();}
                    send_Broadcast_msg = true;
                    broadcast_msg = "Prayer in progress";
                }
                if (zuhr_prayer_In_Progress_notification && cal.after(zuhr_jamaat_cal) && cal.before(zuhr_plus15_cal))
                {
                    zuhr_prayer_In_Progress_notification = false;
                    try {push.sendMessage("Zuhr Jamaa at Daar Ibn Abass has just started"); System.out.println("Prayer in progress notification sent");} catch (IOException e){e.printStackTrace();}
                    send_Broadcast_msg = true;
                    broadcast_msg = "Prayer in progress";
                }
                if (asr_prayer_In_Progress_notification && cal.after(asr_jamaat_cal) && cal.before(asr_jamaat_update_cal))
                {
                    asr_prayer_In_Progress_notification = false;
                    try {push.sendMessage("Asr Jamaa at Daar Ibn Abass has just started"); System.out.println("Prayer in progress notification sent");} catch (IOException e){e.printStackTrace();}
                    send_Broadcast_msg = true;
                    broadcast_msg = "Prayer in progress";
                }


                if (maghrib_prayer_In_Progress_notification && cal.after(maghrib_cal) && cal.before(maghrib_plus15_cal))
                {
                    maghrib_prayer_In_Progress_notification = false;
                    try {push.sendMessage("Maghrib Jamaa at Daar Ibn Abass has just started"); System.out.println("Prayer in progress notification sent");} catch (IOException e){e.printStackTrace();}
                    send_Broadcast_msg = true;
                    broadcast_msg = "Prayer in progress";
                }

                if (isha_prayer_In_Progress_notification && cal.after(isha_jamaat_cal) && cal.before(isha_jamaat_update_cal))
                {
                    isha_prayer_In_Progress_notification = false;
                    try {push.sendMessage("Isha Jamaa at Daar Ibn Abass has just started"); System.out.println("Prayer in progress notification sent");} catch (IOException e){e.printStackTrace();}
                    send_Broadcast_msg = true;
                    broadcast_msg = "Prayer in progress";
                }


                if(send_Broadcast_msg)
                {
                    try
                    {
                        send_Broadcast_msg = false;
                        socket1 = new DatagramSocket(null);
                        socket1.setBroadcast(true);
                        buf1 = broadcast_msg.getBytes();
                        group = InetAddress.getByName("255.255.255.255");
                        packet1 = new DatagramPacket(buf1, buf1.length, group, 8888);
                        socket1.send(packet1);
                    }
                    catch(Exception e){logger.warn("Unexpected error", e);}
                }
            }



            if (System.nanoTime() > sensor1_lastTimerCall + delay_switch_back_to_App && sonar_distance>sonar_active_distance && camera && !manual_Camera && !friday_jamaat_cam_activated)
            {

                System.out.println("Switching back to App...");
//                                ProcessBuilder processBuilder_camera_off = new ProcessBuilder("bash", "-c", "sudo pkill raspistill");
                try {Process process = processBuilder_camera_off.start(); }
                catch (IOException e) {logger.warn("Unexpected error", e);}
                camera = false;
                prayer_In_Progress = false;
                if(local_HDMI_control)
                {
                //                                    ProcessBuilder processBuilder1 = new ProcessBuilder("bash", "-c", "echo \"as\" | cec-client -d 1 -s \"standby 0\" RPI");
                    hdmiOn = true;
                    sensor_lastTimerCall =  System.nanoTime();
                    System.out.println("Tv turned on");
                    try
                    {
                        Thread.sleep(2500);
                        processBuilder1.start();
                        Thread.sleep(2500);
                        processBuilder1.start();
                    }
                    catch (IOException e) {logger.warn("Unexpected error", e);} catch (InterruptedException ex) {
                        java.util.logging.Logger.getLogger(JavaFXApplication4.class.getName()).log(Level.SEVERE, null, ex);}
                }
            }
        }
        
        
    }
    catch(SerialPortException ex)
    {
        System.out.println(" ==>> SERIAL SETUP FAILED : " + ex.getMessage());
        try {push.sendMessage(temp_msg);} catch (IOException e){e.printStackTrace();}
        Thread.currentThread().interrupt();
    } catch (InterruptedException ex) {
        java.util.logging.Logger.getLogger(JavaFXApplication4.class.getName()).log(Level.SEVERE, null, ex);
        
    }
}

    }).start();
}


// Button for manual camera switching========================================================================================================

if (platform.equals("pi") && Button_activated)
{
    new Thread(() ->
    {
        
        
        final GpioController gpioSensor = GpioFactory.getInstance();
        
        
        final GpioPinDigitalInput button = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_UP);
        
        //             final GpioPinDigitalInput button = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_UP);
        System.out.println(" ... Button press function Starting.....");
        button.addListener(new GpioPinListenerDigital()
        {
            @Override 
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event1)
            {
                
                if (event1.getState().isHigh())
                {
                    
                }
                
                if(event1.getState().isLow())
                {
                    if(!manual_Camera)
                    {
                        System.out.println("button pressed ");
//                                ProcessBuilder processBuilder_camera_on = new ProcessBuilder("bash", "-c", "raspistill -vf -p '25,12,670,480'  -t 5400000 -tl 200000 -w 640 -h 400 -o cam2.jpg");
try {Process process = processBuilder_camera_on.start(); System.out.println("Manually Switching to Camera...");}
catch (IOException e) {logger.warn("Unexpected error", e);}
delay_Manual_switch_to_cam_lastTimerCall = System.nanoTime();
camera = true;
manual_Camera = true;
                    }
                }
            }
        });
        
        
        for (;;)
        {
            try
            {
                if (System.nanoTime() > delay_Manual_switch_to_cam_lastTimerCall + delay_Manual_switch_to_cam && sonar_distance>sonar_active_distance && camera && manual_Camera)
                {
                    camera = false;
                    manual_Camera = false;
                    System.out.println("Manually Switching back to App...");
//                                ProcessBuilder processBuilder_camera_off = new ProcessBuilder("bash", "-c", "sudo pkill raspistill");
try {Process process = processBuilder_camera_off.start(); }
catch (IOException e) {logger.warn("Unexpected error", e);}

                }
                
   
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                gpioSensor.shutdown();
                Thread.currentThread().interrupt();
            }
        }
        
    }).start();
    
}

// PIR sensor thread to turn on/Off TV screen to save energy ===============================================================

if (platform.equals("pi"))
{
    new Thread(() ->
    {
        final GpioController gpioSensor = GpioFactory.getInstance();
        sensor_lastTimerCall =  System.nanoTime();
        final GpioPinDigitalInput sensor = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
        sensor.addListener(new GpioPinListenerDigital()
        {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) 
            {
                if (event.getState().isHigh())
                {
                    sensor_lastTimerCall =  System.nanoTime();
                    if(!hdmiOn && !pir_disactive_startup && !prayer_In_Progress)
                    {
//                                ProcessBuilder processBuilder1 = new ProcessBuilder("bash", "-c", "echo \"as\" | cec-client -d 1 -s \"standby 0\" RPI");
                        hdmiOn = true;
                        startup = false;
                        System.out.println("Tv turned on");
                        try
                        {
                            Thread.sleep(2500);
                            processBuilder1.start();
                            Thread.sleep(2500);
                            processBuilder1.start();
                        }
                        catch (IOException e) {logger.warn("Unexpected error", e);} catch (InterruptedException ex) {
                            java.util.logging.Logger.getLogger(JavaFXApplication4.class.getName()).log(Level.SEVERE, null, ex);}
                    }}
                
                if(event.getState().isLow()){sensorLow = true;}
            }
        });
        
        System.out.println(" ... Motion Detection Starting.....");
        
        for (;;)
        {
            try 
            {
                
                if (System.nanoTime() > sensor_lastTimerCall + delay_turnOffTV_after_inactivity && sensorLow && hdmiOn || startup)
                {
                    startup = false;
                    System.out.println("All is quiet...");
//                             ProcessBuilder processBuilder2 = new ProcessBuilder("bash", "-c", "echo \"standby 0000\" | cec-client -d 1 -s \"standby 0\" RPI");
hdmiOn = false;
try {Process process2 = processBuilder2.start();}
catch (IOException e) {logger.warn("Unexpected error", e);}
sensor_lastTimerCall = System.nanoTime();
sensorLow = false;
                }
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                gpioSensor.shutdown();
                Thread.currentThread().interrupt();
            }
        }
        
    }).start();
}




// //UDP thread to receive commands i.e. turn on/Off TV screen when Prayer starts ===============================================================
new Thread(() ->
{
    
    System.out.println(" ... UDP Process Starting.....");
    for (;;)
    {
        try
        {
            DatagramSocket socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);
            byte[] buf = new byte[512];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            while (true)
            {
                
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("UDP Packet received: " + received);
                
                if(received.equals("Prayer in progress") && remote_HDMI_control)
                {
//                            ProcessBuilder processBuilder2 = new ProcessBuilder("bash", "-c", "echo \"standby 0000\" | cec-client -d 1 -s \"standby 0\" RPI");
                    try
                    {
                        if (hdmiOn)
                        {
                            Process process2 = processBuilder2.start();
                            System.out.println("Prayer in Progress...Turning Off TV(s)");
                            Thread.sleep(1000);
                            hdmiOn = false;
                            prayer_In_Progress = true;
                            
                        }
                    }
                    catch (IOException e) {logger.warn("Unexpected error", e);}
                    
                }
                
                if(received.equals("Prayer ended") && remote_HDMI_control)
                {
                    prayer_In_Progress = false;
//                            ProcessBuilder processBuilder1 = new ProcessBuilder("bash", "-c", "echo \"as\" | cec-client -d 1 -s \"standby 0\" RPI");
hdmiOn = true;
sensor_lastTimerCall =  System.nanoTime();
System.out.println("Tv turned on");
try
{
    Thread.sleep(2500);
    processBuilder1.start();
    Thread.sleep(2500);
    processBuilder1.start();
}
catch (IOException e) {logger.warn("Unexpected error", e);} catch (InterruptedException ex) {
    java.util.logging.Logger.getLogger(JavaFXApplication4.class.getName()).log(Level.SEVERE, null, ex);}



                }
                
                
                else if(received.equals("refresh background")) 
                {
                    
                    
                        System.out.println("Changing Background...");
                        images = new ArrayList<String>();
                        //change on osx
                        if (platform.equals("osx"))
                            //        {directory = new File("/Users/ossama/Projects/Pi/javafx/prayertime/background/");}
                        {
                            
                             
                            
                            if (vertical)
                            {
                                directory = new File("/Users/ossama/Dropbox/Projects/Pi/javafx/prayertime/background/vertical");
                            }
                            else {directory = new File("/Users/ossama/Dropbox/Projects/Pi/javafx/prayertime/background/horizontal_HD");}
                            
                        }
                        //        {directory = new File("/Users/samia/NetBeansProjects/prayertime_files/background/");}
                        //change on Pi
                        if (platform.equals("pi"))
                        {
                            if (vertical) 
                            {
                                directory = new File("/home/pi/prayertime/Images/vertical");
                            }
                            else {directory = new File("/home/pi/prayertime/Images/horizontal");}
                            
                            
                        }
                        
                        files = directory.listFiles();
                        for(File f : files) 
                        {
                            if(!f.getName().startsWith(".")){images.add(f.getName());}
                            
                        }
//                        System.out.println(images);
                        countImages = images.size();
                        imageNumber = (int) (Math.random() * countImages);
                        rand_Image_Path = directory + "/"+ images.get(imageNumber);
                        System.out.println(rand_Image_Path);
                        File file = new File(rand_Image_Path);
                        
            
            
                                    
                        
                        
                        
                        Platform.runLater(new Runnable() {
                            @Override public void run()
                            {
                                Image image = new Image(file.toURI().toString());
                                background = new ImageView(image);     
                                background.setFitWidth(1920);
                                background.setFitHeight(1080);
                                background.setPreserveRatio(true);
                                Mainpane.getChildren().removeAll(background);
                                Mainpane.getChildren().add(background);
                                background.setTranslateY(525);  
                                background.toBack();
                            }
                        });     }
                    
                    
                
                
                
                else if(received.equals("hide mainpain"))
                {
                    
                    Platform.runLater(new Runnable()
                    {
                        @Override public void run()
                        {
                            
//                            Mainpane.setVisible(false);
                            if (!orientation.equals("camera_mode") )
                            {
                            hadithPane.setVisible(false);
                            prayertime_pane.setVisible(false);
                            }
                            
                            Mainpane.getStyleClass().clear();
                            Mainpane.getChildren().removeAll(background);
                            
//                            scene.setRoot(clockPane);
                            
//                            stage.initStyle(StageStyle.TRANSPARENT);
                            
                        }
                    });
                }
                
                else if(received.equals("show mainpain"))
                {
                    
                    Platform.runLater(new Runnable() 
                    {
                        @Override public void run()
                        {
                            
                            hadithPane.setVisible(true);
                            prayertime_pane.setVisible(true);
                            Mainpane.getChildren().add(background);
                            
                            if (orientation.equals("horizontal") )
                            {
                                background.setTranslateY(232);
                            }
                            else if (orientation.equals("horizontal_HD") )
                            {
                                background.setTranslateY(525);
                            }
                            
                            background.toBack();
                            
                        } 
                    });
                }
                
                
                else if(received.equals("show radar"))
                {
                    manual_radar_display = true;
                    Platform.runLater(new Runnable()
                    {
                        @Override public void run()
                        {
                            
//                                        thermoMeter.setTranslateX(300);
//                                        thermoMeter.setTranslateY(100);
                            Mainpane.add(thermoMeter, 0, 27,2,2);
                            radar_gauge_anninmation.start();
                        }
                    }); 
                    
                    
                    
                }
                
                
                else if(received.equals("update sonar settings"))
                {
                    try
                    {
                        c = DBConnect.connect();
                        SQL = "Select sonar_active,sonar_active_distance  from settings";
                        rs = c.createStatement().executeQuery(SQL);
                        while (rs.next())
                        {
                            
                            sonar_active =                  rs.getBoolean("sonar_active");
                            sonar_active_distance =         rs.getInt("sonar_active_distance");
                            
                            
                        }
                        c.close();
                        
                        
                        
                    }
                    catch (Exception e){logger.warn("Unexpected error", e);}
                    
                } 
                
                
                
                
                else if(received.equals("hide radar"))
                {
                    
                    manual_radar_display = false;
                    Platform.runLater(new Runnable()
                    {
                        @Override public void run()
                        {
                            Mainpane.getChildren().remove(thermoMeter);
                            radar_gauge_anninmation.stop();
                        }
                    });
                     
                    
                    
                }
                
                else if(received.equals("video toggle"))
                {
                    if (!camera)
                    {
                        Platform.runLater(new Runnable()
                        {
                            @Override public void run()
                            {
//                                        Moonpane.setVisible(false);
//                                        prayertime_pane.setVisible(false);
//                                        hadithPane.setVisible(false);
                                camera = true;
                                sensor1_lastTimerCall = System.nanoTime();
//                                        camera_Timeline.play();
//                                        
//                                        text_Box.setVisible(false);
//                                        ar_Marquee_Notification_Text.setVisible(false);
//                                        ar_timeline.stop();
//                                        en_Marquee_Notification_Text.setVisible(false);
//                                        en_timeline.stop();
                                        
//                                        ProcessBuilder processBuilder_camera_on = new ProcessBuilder("bash", "-c", "raspistill -vf -p '25,12,670,480'  -t 5400000 -tl 200000 -w 640 -h 400 -o cam2.jpg");
try {Process process = processBuilder_camera_on.start(); }
catch (IOException e) {logger.warn("Unexpected error", e);}

                            }
                        });
                        
                    }
                    else
                    {
                        Platform.runLater(new Runnable()
                        {
                            @Override public void run()
                            {

//                                        camera_Timeline.stop();
//                                        Mainpane.getChildren().removeAll(myGroup); 
//                                        Moonpane.setVisible(true);
//                                        prayertime_pane.setVisible(true);
//                                        hadithPane.setVisible(true);
//                                        ProcessBuilder processBuilder_camera_off = new ProcessBuilder("bash", "-c", "sudo pkill raspistill");
                                try {Process process = processBuilder_camera_off.start(); }
                                catch (IOException e) {logger.warn("Unexpected error", e);}
                                camera = false;
                            }
                        });
                    }
                }
                
                
                
                else if(received.equals("refresh hadith"))
                {
                    
                    System.out.println("Getting Hadith...");
                    
                    
                    try
                    {
                        c = DBConnect.connect();
                        
                        if (dtIslamic.getMonthOfYear()==9){SQL ="select hadith, translated_hadith from hadith WHERE topic = 'fasting' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}
//                                if (dtIslamic.getMonthOfYear()==9){SQL ="select hadith, translated_hadith from hadith WHERE ID = 2872";}
                        
                        else if(dtIslamic.getMonthOfYear()==12){SQL ="select hadith, translated_hadith from hadith WHERE topic = 'Hajj (Pilgrimage)' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}
                        else if (dayofweek_int == 5 || dayofweek_int == 6){SQL = "select hadith, translated_hadith from hadith WHERE day = '5' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";}
                        else
                        {
                            SQL = "select * from hadith WHERE day = '0' and CHAR_LENGTH(translated_hadith)<"+ max_en_hadith_len + " and CHAR_LENGTH(hadith)<" + max_ar_hadith_len + " ORDER BY RAND( ) LIMIT 1";
//                                    SQL = "select * from hadith where  CHAR_LENGTH(translated_hadith)>527"; // the bigest Hadith
                        }
                        rs = c.createStatement().executeQuery(SQL);
                        while (rs.next())
                        {
                            hadith = rs.getString("hadith");
                            translated_hadith = rs.getString("translated_hadith");
                            
                        }
                        c.close();
                        System.out.println("arabic hadith length" + hadith.length());
                        System.out.println(" english hadith length" + translated_hadith.length());
                        
                    } 
                    catch (Exception e){logger.warn("Unexpected error", e);}
                    
                }
                
                
                
                if(received.equals("prayer call"))
                {
//                            ProcessBuilder processBuilder_Athan = new ProcessBuilder("bash", "-c", "mpg123 /home/pi/prayertime/Audio/athan1.mp3");
                    
                    try {Process process = processBuilder_Athan.start();}
                    catch (IOException e) {logger.warn("Unexpected error", e);}
                }
                
                if(received.equals("snapshot"))
                {
                    System.out.println("saving...");
//                            WritableImage snapshot_prayertime_pane = prayertime_pane.getScene().snapshot(null);
//                            WritableImage snapshot_full = scene.snapshot(null);
//                            ImageIO.write(SwingFXUtils.fromFXImage(snapshot_full, null), "png", file);

WritableImage image = new WritableImage(400, 400);
scene.snapshot(image);
File outputfile = new File("saved.png");
ImageIO.write((RenderedImage) image, "png", outputfile);




System.out.println("saved...");
                }
                
                if(received.equals("refresh facebook"))
                {
                    if (facebook_Receive)
                    {
//                                getFacebook = false;
                        facebook_Text_Post = false;
                        facebook_Picture_Post = false;
                        facebook_post = "";
                        //                            facebook_Post_Url = "";
                        facebook_Fan_Count = "";
                        Calendar facebook_created_time_calendar = null;
                        Calendar facebook_photo_created_time_calendar = null;
                        Calendar facebook_check_post_date = Calendar.getInstance();
                        facebook_check_post_date.add(Calendar.DAY_OF_MONTH, -6);
                        long facebook_check_post_Unix_Time = facebook_check_post_date.getTimeInMillis() / 1000;
                        //                            out.println(facebook_check_post_Unix_Time);
                        String query = "SELECT message,timeline_visibility, created_time   FROM stream WHERE source_id = " + page_ID + " AND message AND strlen(attachment.fb_object_type) < 1 AND type != 56 AND type = 46  AND strpos(message, \"prayer time(s)\") < 0 AND strpos(message, \"White days\") < 0 AND strpos(message, \"Hadith of the Day:\") < 0 AND created_time > " + facebook_check_post_Unix_Time + " LIMIT 1";
                        //                            String query = "{\"messages\":\"SELECT message,timeline_visibility, created_time   FROM stream WHERE source_id = " + page_ID + " AND message AND strlen(attachment.fb_object_type) < 1 AND type != 56 AND type = 46  AND strpos(message, \'prayer time(s)\') < 0 AND strpos(message, \'White days\') < 0 AND strpos(message, \'Hadith of the Day:\') < 0 AND created_time > " + facebook_check_post_Unix_Time + " LIMIT 1\" ,  \"count\": \"SELECT fan_count FROM page WHERE page_id = " + page_ID + "\"}";
                        //                            out.println(query);
                        try
                        {
                            List<JsonObject> queryResults = facebookClient.executeFqlQuery(query, JsonObject.class);
                            
                            if(!queryResults.isEmpty())
                            {
                                JsonObject facebookPost_J = queryResults.get(0);
                                facebook_post = facebookPost_J.getString("message");
                                
                                //                                    facebook_post = "Asalamualaikum,\n" + "We have been given a large printer/copier for administration at Daar Ibn\n Abbas. Is there any brothers available to pick it up from Lakemba? ";
                                
                                String[] lines = facebook_post.split("\r\n|\r|\n");
                                
                                if(null != facebook_post && !"".equals(facebook_post)) 
                                {
                                    if(facebook_post.contains("\n\n"))
                                    {
                                        out.println("'/n/n' detected");
                                        facebook_post =facebook_post.replace("\n\n", "\n");
                                        out.println(facebook_post);
                                    }
                                    facebook_created_time_calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone_ID));
                                    facebook_created_time_calendar.setTimeInMillis(queryResults.get(0).getLong("created_time")* 1000);
                                    //                                        out.print("Comment posted on:"); out.println(facebook_created_time_calendar.getTime());
                                    if(facebook_post.contains("tonight") || facebook_post.contains("today") && Days.daysBetween(new DateMidnight(DateTime_now), new DateMidnight(facebook_created_time_calendar)).getDays() != 0)
                                    {
                                        out.println("Facebook post contains either  the word 'today' or 'tonight' and has not been posted today");
                                        facebook_post = "";
                                        facebook_Label_visible = false;
                                    }
                                    
                                    else if (facebook_post.length() > 390 || lines.length > 6)
                                    {
                                        System.out.println("Facebook post is too large, it will not be posted");
                                        System.out.println("Facebook lines: " +lines.length);
                                        System.out.println("Facebook string length: " + facebook_post.length());
                                        facebook_post = "";
                                        facebook_Label_visible = false;
                                    }
                                    
                                    else
                                    {
                                        facebook_Text_Post = true;
                                        facebook_Label_visible = true;
                                        facebook_Label_visible_set_once = true;
                                    }
                                }
                            }
                            else{out.println("Facebook post is empty");}
                        }
                        catch (FacebookException e){logger.warn("Unexpected error", e);}
                        catch (Exception e){logger.warn("Unexpected error", e);}
                        
                        query = "SELECT fan_count FROM page WHERE page_id = " + page_ID ;
                        try 
                        {
                            List<JsonObject> queryResults = facebookClient.executeFqlQuery(query, JsonObject.class);
                            facebook_Fan_Count = queryResults.get(0).getString("fan_count");
                            out.println("Page Likes: " + facebook_Fan_Count);
                            
                        }
                        catch (FacebookException e){logger.warn("Unexpected error", e);}
                        catch (Exception e){logger.warn("Unexpected error", e);} 
                        query = "SELECT attachment.media.photo.images.src, created_time   FROM stream WHERE source_id = " + page_ID + "  AND type = 247 AND created_time > " + facebook_check_post_Unix_Time + " LIMIT 1";
                        try
                        {
                            List<JsonObject> queryResults = facebookClient.executeFqlQuery(query, JsonObject.class);
                            if(!queryResults.isEmpty())
                            {
                                if(null != facebook_Post_Url && !"".equals(facebook_Post_Url)){ old_facebook_Post_Url = new String(facebook_Post_Url);}
                                //                                    out.println(old_facebook_Post_Url);
                                
                                facebook_Post_Url = queryResults.get(0).getJsonObject("attachment").getJsonArray("media").getJsonObject(0).getJsonObject("photo").getJsonArray("images").getJsonObject(1).getString("src");
                                //                                    out.println(facebook_Post_Url);
                                
                                facebook_photo_created_time_calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone_ID));
                                facebook_photo_created_time_calendar.setTimeInMillis(queryResults.get(0).getLong("created_time")* 1000);
                                out.print("Comment posted on:"); out.println(facebook_photo_created_time_calendar.getTime());
                                
                                if(null != facebook_Post_Url && !"".equals(facebook_Post_Url) ) 
                                {
                                    if(null != old_facebook_Post_Url && !"".equals(old_facebook_Post_Url)) 
                                    {
                                        if(facebook_Post_Url.equals(old_facebook_Post_Url))
                                        {out.print("Facebook photo post has not changed from previous fetch, nothing has been set");}
                                        
                                        if(!facebook_Post_Url.equals(old_facebook_Post_Url)) 
                                        {
                                            facebook_Picture_Post = true;
                                            facebook_Label_visible = true;
                                            facebook_Label_visible_set_once = true;
                                        }
                                    }

                                    else
                                    {
                                        facebook_Picture_Post = true;
                                        facebook_Label_visible = true;
                                        facebook_Label_visible_set_once = true;
                                    }
                                }  
                            }
                            
                        }
                        catch (FacebookException e){logger.warn("Unexpected error", e);}
                        catch (Exception e){logger.warn("Unexpected error", e);}
                        
                        //compare text and picture post dates, if facebook_Picture_Post && facebook_Text_Post are true, and dates are not null
                        // which ever was posted last, clear facebook_post = ""; or facebook_Post_Url = "";
                        if(facebook_Picture_Post && facebook_Text_Post)
                        {
                            if (facebook_photo_created_time_calendar.before(facebook_created_time_calendar))
                            {
                                facebook_Post_Url = "";
                                
                            }
                            
                            else
                            {
                                facebook_post = "";
                                
                            }
                        }
                    }
                }

            }
        }
        
//                 catch(InterruptedException e){Thread.currentThread().interrupt();}
        catch (Exception e){logger.warn("Unexpected error", e); Thread.currentThread().interrupt();}
        
    }
    
}).start();




        
        
        
        
        
        
    }
//===============================================================================================================================================
    
    @Override public void start(Stage stage) throws MalformedURLException {
        
//        Platform.setImplicitExit(false);
        Pane root = new Pane();
        
        // rotate tv screen to portrait mode
        // edit the /boot/config.txt file Copy stored in documentation folder (i.e. framebuffer_width=1080   framebuffer_height=1920  display_rotate=1...)
//#VERTICAL##################################        
        if (orientation.equals("vertical") )
        {
            scene = new Scene(root, 1080, 1920);
            scene.getStylesheets().addAll(this.getClass().getResource("style_90.css").toExternalForm());
            try
            {
                String image = new File(rand_Image_Path).toURI().toURL().toString();
//                System.out.println("image string: " + image);
                Mainpane = new GridPane();
                Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-image-repeat: repeat; -fx-background-size: 1080 1920;-fx-background-position: bottom left;");  
            }
            catch (IOException e) {logger.warn("Unexpected error", e);}
            
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            
            Mainpane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/13.0).build()       
            );
            Mainpane.getRowConstraints().setAll(
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/24.0).build()
            );
    //        Mainpane.setGridLinesVisible(true);
            Mainpane.setId("Mainpane");
            prayertime_pane = prayertime_pane();    
            if(moon_calcs_display)
            {
                    Moonpane =   moonpane();
            }
            if(moon_calcs_display)
            {Sunrisepane =   sunrise();}
            if(weather_enabled){
                Weatherpane =   weatherpane();
                Weatherpane.setVisible(false);
            }
            if(moon_calcs_display)
            {Sunrisepane.setVisible(false);}
            hadithPane = hadithPane();
            clockPane =   clockPane();
            GridPane footerPane =   footerPane();

            ar_Marquee_Notification_Text = new Text(ar_Marquee_Notification_string);
            ar_Marquee_Notification_Text.setTextAlignment(TextAlignment.RIGHT);
            ar_Marquee_Notification_Text.setY(35);
            ar_Marquee_Notification_Text_textSize = 38;
            ar_Marquee_Notification_Text.setFont(Font.font("Verdana", ar_Marquee_Notification_Text_textSize));                        
            ar_Marquee_Notification_Text.setFill(Color.WHITE);
            ar_Marquee_Notification_Text.setFontSmoothingType(FontSmoothingType.LCD);
            ar_Marquee_Notification_Text_XPos = 320;
            ar_Marquee_Notification_Text.setX(1920/2 - ar_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);
            en_Marquee_Notification_Text = new Text(en_Marquee_Notification_string);   
            en_Marquee_Notification_Text.setTextAlignment(TextAlignment.LEFT);                    
            en_Marquee_Notification_Text.setY(40);
            en_Marquee_Notification_Text_textSize = 37;
            en_Marquee_Notification_Text.setFont(Font.font("Verdana", en_Marquee_Notification_Text_textSize));                        
            en_Marquee_Notification_Text.setFill(Color.WHITE);
            en_Marquee_Notification_Text.setFontSmoothingType(FontSmoothingType.LCD);
            en_Marquee_Notification_Text.setX(1920/2 - en_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);  


//            ImageView notification_image = new ImageView(new Image(getClass().getResourceAsStream("/Images/notification.png")));
//            notification_image.setTranslateY(0);
//            notification_image.setFitHeight(25);
//            notification_image.setPreserveRatio(true);
            text_Box = new Pane();
            text_Box.setMinWidth(640);
            text_Box.setMinHeight(23);
            text_Box.getChildren().addAll(ar_Marquee_Notification_Text, en_Marquee_Notification_Text);
            text_Box.setId("notification"); 


                Mainpane.add(text_Box,0,0,30,1);
                text_Box.setTranslateY(5);

      //============================================

            DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);

    //        clock.setEffect(ds);
            if(moon_calcs_display) {Moonpane.setEffect(ds);}
            if(moon_calcs_display)
            {Sunrisepane.setEffect(ds);}
            if(weather_enabled){Weatherpane.setEffect(ds);}
            prayertime_pane.setEffect(ds);
            hadithPane.setEffect(ds);
            clockPane.setEffect(ds);
    //        footerPane.setEffect(ds);
      //============================================
            Mainpane.add(clockPane, 1, 1,5,1);
            if(moon_calcs_display) {Mainpane.add(Moonpane, 7, 1);}
            if(weather_enabled){Mainpane.add(Weatherpane, 7, 1);}
            if(moon_calcs_display)
            {Mainpane.add(Sunrisepane, 7, 1);}
            Mainpane.add(prayertime_pane, 1, 5,11,7);  
            Mainpane.add(hadithPane, 1, 15,11,13);
            prayertime_pane.setTranslateY(30);
    //        hadithPane.setTranslateY(0);
            Mainpane.add(footerPane, 1, 20,11,1);
    //        footerPane.setTranslateY(514);
    //        Mainpane.setCache(true);

        }
        
        
//#SD#########################################
        else if (orientation.equals("camera_mode") )
        {
            
            scene = new Scene(root,690,480);
            scene.getStylesheets().addAll(this.getClass().getResource("style_camera_mode.css").toExternalForm());
            stage.setX(25);
            stage.setY(10);
//            try
//            {
//                String image = new File(rand_Image_Path).toURI().toURL().toString();
//                System.out.println("image string: " + image);
                Mainpane = new GridPane();
//            }
//            catch (IOException e) {logger.warn("Unexpected error", e);}
//            
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.TRANSPARENT);
            
            Mainpane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build()       
            );
            Mainpane.getRowConstraints().setAll(
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build()
            );
//            Mainpane.setGridLinesVisible(true);
//            Mainpane.setId("Mainpane");

            Glasspane = new GridPane();
            Glasspane.setId("glass");       
            if(moon_calcs_display) {Moonpane =   moonpane();}
            if(weather_enabled){
                Weatherpane =   weatherpane();
            }
            if(moon_calcs_display){Sunrisepane =   sunrise();}
//            Weatherpane.setVisible(false);
            if(moon_calcs_display){Sunrisepane.setVisible(false);}
            clockPane =   clockPane();

              
            ar_Marquee_Notification_Text = new Text(ar_Marquee_Notification_string);
            ar_Marquee_Notification_Text.setTextAlignment(TextAlignment.RIGHT);
            ar_Marquee_Notification_Text.setY(15);
            ar_Marquee_Notification_Text_textSize = 13;
            ar_Marquee_Notification_Text.setFont(Font.font("Verdana", ar_Marquee_Notification_Text_textSize));                        
            ar_Marquee_Notification_Text.setFill(Color.WHITE);
            ar_Marquee_Notification_Text.setFontSmoothingType(FontSmoothingType.LCD);
            ar_Marquee_Notification_Text_XPos = 320;
            ar_Marquee_Notification_Text.setX(680/2 - ar_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);
            en_Marquee_Notification_Text = new Text(en_Marquee_Notification_string);   
            en_Marquee_Notification_Text.setTextAlignment(TextAlignment.LEFT);                    
            en_Marquee_Notification_Text.setY(15);
            en_Marquee_Notification_Text_textSize = 13;
            en_Marquee_Notification_Text.setFont(Font.font("Verdana", en_Marquee_Notification_Text_textSize));                        
            en_Marquee_Notification_Text.setFill(Color.WHITE);
            en_Marquee_Notification_Text.setFontSmoothingType(FontSmoothingType.LCD);
            en_Marquee_Notification_Text.setX(680/2 - en_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);
//            ImageView notification_image = new ImageView(new Image(getClass().getResourceAsStream("/Images/notification.png")));
//            notification_image.setTranslateY(0);
//            notification_image.setFitHeight(25);
//            notification_image.setPreserveRatio(true);
            text_Box = new Pane();
            text_Box.setMinWidth(680);
            text_Box.setMinHeight(20);
            text_Box.getChildren().addAll(ar_Marquee_Notification_Text, en_Marquee_Notification_Text);
            text_Box.setId("notification"); 
            
            
            
            

            Mainpane.add(Glasspane, 0, 0,30, 7);
            Mainpane.add(clockPane, 0, 1,23,2);

            if(moon_calcs_display) {Mainpane.add(Moonpane, 18, 3);}
            if(weather_enabled){
                Mainpane.add(Weatherpane, 12, 3);
            }
           if(moon_calcs_display){ Mainpane.add(Sunrisepane, 20, 3);}
            
            
            
            Mainpane.add(text_Box,0,29,30,1);
//            text_Box.setTranslateX(5);
            text_Box.setTranslateY(-7);
//            Glasspane.setTranslateX(5);
            Glasspane.setTranslateY(-40);
            clockPane.setTranslateX(-8);
            if(moon_calcs_display) {Moonpane.setTranslateX(35);}
            clockPane.setTranslateY(-25);
            if(moon_calcs_display) {Moonpane.setTranslateY(-21);}
            if(moon_calcs_display){Sunrisepane.setTranslateY(-21);
            Sunrisepane.setTranslateX(20);}
            if(weather_enabled){
                Weatherpane.setTranslateY(-21);
                Weatherpane.setTranslateX(-13);
            }

             //============================================

            DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);
            
            DropShadow ds1 = new DropShadow();
            ds1.setOffsetY(5.0);
            ds1.setOffsetX(5.0);
            ds1.setColor(Color.BLACK);

            if(moon_calcs_display) {Moonpane.setEffect(ds);}
            if(moon_calcs_display){Sunrisepane.setEffect(ds);}
            if(weather_enabled){Weatherpane.setEffect(ds);}
            clockPane.setEffect(ds);
            Glasspane.setEffect(ds1);
            
           
            
        }        
        
        
//#SD#########################################
        else if (orientation.equals("horizontal") )
        {
            scene = new Scene(root,660,480);
            scene.getStylesheets().addAll(this.getClass().getResource("style_SD.css").toExternalForm());
            stage.setX(25);
            stage.setY(10);
//            try
//            {
//                String image = new File(rand_Image_Path).toURI().toURL().toString();
//                System.out.println("image string: " + image);
                Mainpane = new GridPane();
//                Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-image-repeat: repeat; -fx-background-size: 660 480; -fx-background-position: bottom left;");  
//            }
//            catch (IOException e) {logger.warn("Unexpected error", e);}
//            
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.TRANSPARENT);
            
            Mainpane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/30.0).build()       
            );
            Mainpane.getRowConstraints().setAll(
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/26.0).build()
            );
//            Mainpane.setGridLinesVisible(true);
//            Mainpane.setId("Mainpane");

            Glasspane = new GridPane();
            Glasspane.setId("glass");       
            prayertime_pane = prayertime_pane();    
            if(moon_calcs_display) {Moonpane =   moonpane();}
            if(weather_enabled){
                Weatherpane =   weatherpane();
            }
            if(moon_calcs_display){Sunrisepane =   sunrise();}
//            Weatherpane.setVisible(false);
            if(moon_calcs_display){Sunrisepane.setVisible(false);}
            hadithPane = hadithPane();
            clockPane =   clockPane();
            GridPane footerPane =   footerPane();

              
            ar_Marquee_Notification_Text = new Text(ar_Marquee_Notification_string);
            ar_Marquee_Notification_Text.setTextAlignment(TextAlignment.RIGHT);
            ar_Marquee_Notification_Text.setY(15);
            ar_Marquee_Notification_Text_textSize = 13;
            ar_Marquee_Notification_Text.setFont(Font.font("Verdana", ar_Marquee_Notification_Text_textSize));                        
            ar_Marquee_Notification_Text.setFill(Color.WHITE);
            ar_Marquee_Notification_Text.setFontSmoothingType(FontSmoothingType.LCD);
            ar_Marquee_Notification_Text_XPos = 320;
            ar_Marquee_Notification_Text.setX(660/2 - ar_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);
            en_Marquee_Notification_Text = new Text(en_Marquee_Notification_string);   
            en_Marquee_Notification_Text.setTextAlignment(TextAlignment.LEFT);                    
            en_Marquee_Notification_Text.setY(15);
            en_Marquee_Notification_Text_textSize = 13;
            en_Marquee_Notification_Text.setFont(Font.font("Verdana", en_Marquee_Notification_Text_textSize));                        
            en_Marquee_Notification_Text.setFill(Color.WHITE);
            en_Marquee_Notification_Text.setFontSmoothingType(FontSmoothingType.LCD);
            en_Marquee_Notification_Text.setX(660/2 - en_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);
//            ImageView notification_image = new ImageView(new Image(getClass().getResourceAsStream("/Images/notification.png")));
//            notification_image.setTranslateY(0);
//            notification_image.setFitHeight(25);
//            notification_image.setPreserveRatio(true);
            text_Box = new Pane();
            text_Box.setMinWidth(660);
            text_Box.setMinHeight(20);
            text_Box.getChildren().addAll(ar_Marquee_Notification_Text, en_Marquee_Notification_Text);
            text_Box.setId("notification"); 
            
            
            if(!custom_background)
            {
                File file = new File(rand_Image_Path);
                Image image = new Image(file.toURI().toString());
                background = new ImageView(image);     
                background.setFitWidth(660);
                background.setFitHeight(480);
    //            background.setPreserveRatio(true);
            }

            else {background.setStyle("-fx-background-color: #CCFF99;");}
                    
                    
            Mainpane.getChildren().add(background);
//            background.setTranslateX(15);
            background.setTranslateY(232);
            
            

            Mainpane.add(Glasspane, 0, 0,30, 7);
            Mainpane.add(clockPane, 0, 1,23,2);

            if(moon_calcs_display) {Mainpane.add(Moonpane, 18, 3);}
            if(weather_enabled){
                Mainpane.add(Weatherpane, 12, 3);
            }
            if(moon_calcs_display){Mainpane.add(Sunrisepane, 20, 3);}
            
            Mainpane.add(prayertime_pane, 16, 8,13,21); 
            Mainpane.add(hadithPane, 1,8,14,21);
            
            
            Mainpane.add(text_Box,0,29,30,1);
            text_Box.setTranslateY(-10);
            prayertime_pane.setTranslateY(-20);
            hadithPane.setTranslateY(-20);
            Glasspane.setTranslateY(-23);
            clockPane.setTranslateX(-8);
            if(moon_calcs_display) {Moonpane.setTranslateX(35);}
            clockPane.setTranslateY(-13);
            if(moon_calcs_display) {Moonpane.setTranslateY(-6);}
            if(moon_calcs_display){Sunrisepane.setTranslateY(-6);
            Sunrisepane.setTranslateX(20);}
            if(weather_enabled){
                Weatherpane.setTranslateY(-6);
                Weatherpane.setTranslateX(-13);
            }

             //============================================

            DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);

            if(moon_calcs_display) {Moonpane.setEffect(ds);}
            if(moon_calcs_display){Sunrisepane.setEffect(ds);}
            if(weather_enabled){Weatherpane.setEffect(ds);}
            prayertime_pane.setEffect(ds);
            hadithPane.setEffect(ds);
            clockPane.setEffect(ds);
            Glasspane.setEffect(ds);
            footerPane.setEffect(ds);
            
            
            
            
        
        }
//#HD#########################################
        else 
        {
            scene = new Scene(root, 1920, 1080);
//            scene = new Scene(root, 320, 240);

            if(custom_background){scene.getStylesheets().addAll(this.getClass().getResource("style_HD_custom.css").toExternalForm());}
            else {scene.getStylesheets().addAll(this.getClass().getResource("style_HD.css").toExternalForm());}
            
                
                Mainpane = new GridPane();
//                Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-image-repeat: repeat; -fx-background-size: 1920 1080; -fx-background-position: bottom left;");  

            Mainpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build(),
                    ColumnConstraintsBuilder.create().percentWidth(100/30.0).build()       
            );
            Mainpane.getRowConstraints().setAll(
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build(),
                    RowConstraintsBuilder.create().percentHeight(100/26.0).build()
            );
//            Mainpane.setGridLinesVisible(true);
//            Mainpane.setId("Mainpane");
            Glasspane = new GridPane();
            Glasspane.setId("glass");       
            prayertime_pane = prayertime_pane();    
            if(moon_calcs_display) {Moonpane =   moonpane();}
            if(show_logo) {Logopane =   logopane();}
            if(weather_enabled){
                
                Weatherpane =   weatherpane();
                Weatherpane.setVisible(true);
            }
            
            
            
//            Moonpane.setVisible(false);
//            Weatherpane.setVisible(false);
            
            
            
            if(moon_calcs_display){Sunrisepane =   sunrise();
            Sunrisepane.setVisible(false);}
            hadithPane = hadithPane();      
            clockPane =   clockPane();
            GridPane footerPane =   footerPane();

            ar_Marquee_Notification_Text = new Text(ar_Marquee_Notification_string);
            ar_Marquee_Notification_Text.setTextAlignment(TextAlignment.RIGHT);
            ar_Marquee_Notification_Text.setY(35);
            ar_Marquee_Notification_Text_textSize = 37;
            ar_Marquee_Notification_Text.setFont(Font.font("Verdana", ar_Marquee_Notification_Text_textSize));                        
            ar_Marquee_Notification_Text.setFill(Color.WHITE);
            ar_Marquee_Notification_Text.setFontSmoothingType(FontSmoothingType.LCD);
            ar_Marquee_Notification_Text_XPos = 320;
            ar_Marquee_Notification_Text.setX(1920/2 - ar_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);
            en_Marquee_Notification_Text = new Text(en_Marquee_Notification_string);   
            en_Marquee_Notification_Text.setTextAlignment(TextAlignment.LEFT);                    
            en_Marquee_Notification_Text.setY(40);
            en_Marquee_Notification_Text_textSize = 33;
            en_Marquee_Notification_Text.setFont(Font.font("Verdana", en_Marquee_Notification_Text_textSize));                        
            en_Marquee_Notification_Text.setFill(Color.WHITE);
            en_Marquee_Notification_Text.setFontSmoothingType(FontSmoothingType.LCD);
            en_Marquee_Notification_Text.setX(1920/2 - en_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);
//            ImageView notification_image = new ImageView(new Image(getClass().getResourceAsStream("/Images/notification.png")));
//            notification_image.setTranslateY(0);
//            notification_image.setFitHeight(50);
    //        twitter_code.setTranslateY(20);
//            notification_image.setPreserveRatio(true);
            text_Box = new Pane();
            text_Box.setMinWidth(640);
            text_Box.setMinHeight(50);
            text_Box.getChildren().addAll(ar_Marquee_Notification_Text, en_Marquee_Notification_Text);
            text_Box.setId("notification"); 

            File file = new File(rand_Image_Path);
            Image image = new Image(file.toURI().toString());
            background = new ImageView(image);     
            background.setFitWidth(1920);
            background.setFitHeight(1080);
            background.setPreserveRatio(true);
            
            Mainpane.getChildren().add(background);
//            background.setTranslateX(400);
            background.setTranslateY(525);
            
            Mainpane.add(Glasspane, 0, 0,30,7);
            
            Mainpane.add(clockPane, 0, 1,23,2);
            if(moon_calcs_display) {Mainpane.add(Moonpane, 19, 3);}
            if(weather_enabled){Mainpane.add(Weatherpane, 14, 3);}
            if(moon_calcs_display){Mainpane.add(Sunrisepane, 20, 3);}
            if(show_logo) {Mainpane.add(Logopane, 19, 3); Logopane.setTranslateX(-75);}
            if(show_friday)
            {   
                Mainpane.add(prayertime_pane, 16, 8,13,21); 
                prayertime_pane.setTranslateY(30); 
                hadithPane.setTranslateY(15); 
                Mainpane.add(hadithPane, 1,8,14,22);
            }
            else
            {   
                Mainpane.add(prayertime_pane, 16, 8,13,21); 
                prayertime_pane.setTranslateY(10);
                Mainpane.add(hadithPane, 1,8,14,21);
            }
            
            
            
            Mainpane.add(text_Box,0,0,30,1);
            text_Box.setTranslateY(5);
            clockPane.setTranslateX(140);
            if(moon_calcs_display) {Moonpane.setTranslateX(150);}
            clockPane.setTranslateY(27);
            if(moon_calcs_display) {Moonpane.setTranslateY(7);}
//            Sunrisepane.setTranslateX(27);
            


             //============================================

            DropShadow ds = new DropShadow();
            ds.setOffsetY(10.0);
            ds.setOffsetX(10.0);
            ds.setColor(Color.BLACK);

            if(moon_calcs_display) {Moonpane.setEffect(ds);}
            if(show_logo) {Logopane.setEffect(ds);}
            if(weather_enabled){Weatherpane.setEffect(ds);}
            if(moon_calcs_display){Sunrisepane.setEffect(ds);}
            prayertime_pane.setEffect(ds);
            hadithPane.setEffect(ds);
            clockPane.setEffect(ds);
            Glasspane.setEffect(ds);
            footerPane.setEffect(ds);
        }
        
        ft_ar.setNode(ar_Marquee_Notification_Text);
        ft_ar.setFromValue(1.0);
        ft_ar.setToValue(0);
        ft_ar.setCycleCount(Timeline.INDEFINITE);
        ft_ar.setAutoReverse(true);
        
        ft_en.setNode(en_Marquee_Notification_Text);
        ft_en.setFromValue(1.0);
        ft_en.setToValue(0);
        ft_en.setCycleCount(Timeline.INDEFINITE);
        ft_en.setAutoReverse(true);
        
        facebook_image_fade_in = new FadeTransition(Duration.millis(500), facebook_Label);
        
        facebook_image_fade_in.setFromValue(0);
        facebook_image_fade_in.setToValue(1);
        facebook_image_fade_in.setCycleCount(1);
        
        facebook_image_fade_out = new FadeTransition(Duration.millis(500), facebook_Label);
        facebook_image_fade_out.setFromValue(1);
        facebook_image_fade_out.setToValue(0);
        facebook_image_fade_out.setCycleCount(1);
        
        hadith_arabic_fade_out = new FadeTransition(Duration.millis(500), facebook_Label);
        hadith_arabic_fade_out.setFromValue(1);
        hadith_arabic_fade_out.setToValue(0);
        hadith_arabic_fade_out.setCycleCount(1);
        
        hadith_arabic_fade_in = new FadeTransition(Duration.millis(500), facebook_Label);
        hadith_arabic_fade_in.setFromValue(1);
        hadith_arabic_fade_in.setToValue(0);
        hadith_arabic_fade_in.setCycleCount(1);
        
        hadith_en_fade_out = new FadeTransition(Duration.millis(500), facebook_Label);
        hadith_en_fade_out.setFromValue(1);
        hadith_en_fade_out.setToValue(0);
        hadith_en_fade_out.setCycleCount(1);
        
        hadith_en_fade_in = new FadeTransition(Duration.millis(500), facebook_Label);
        hadith_en_fade_in.setFromValue(1);
        hadith_en_fade_in.setToValue(0);
        hadith_en_fade_in.setCycleCount(1);
        
        
//        facebook_Label.setVisible(true);
        scene.setFill(Color.TRANSPARENT);
            
        scene.setRoot(Mainpane);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        
//        if (platform.equals("osx")){stage.setAlwaysOnTop(true);}
//        stage.setAlwaysOnTop(true);
        
        stage.show();
        
        translate_timer.start(); 
        clock_update_timer.start();
        
        if (orientation.equals("camera_mode") )
        {
            Mainpane.getStyleClass().clear();
            Mainpane.getChildren().removeAll(background);
        }
                            
                            
        
    }

    
//    private void ar_Animate(){

//                            ar_Marquee_Notification_Text = new Text(ar_Maar_Marquee_Notification_Textrquee_Notification_string);
                            
//                            ar_Marquee_Notification_Text.setX(-960);
                            
                    //        text.setStroke(Color.WHITESMOKE);
                    //        text.setStrokeWidth(0.4);
                            
//                            ar_timeline.setCycleCount(Timeline.INDEFINITE);
//                            final KeyValue ar_keyvalue = new KeyValue(ar_Marquee_Notification_Text.xProperty(), ar_Marquee_Notification_Text.getBoundsInLocal().getWidth());
//                            final KeyFrame ar_keyframe = new KeyFrame(Duration.millis(60000), ar_keyvalue);
//                            ar_timeline.getKeyFrames().add(ar_keyframe);
//                            ar_timeline.play();
                            
                           
                            
//                            ft_ar.playFromStart();
                            
                            
//                            System.out.println("######arabic text width: " + ar_Marquee_Notification_Text.getBoundsInLocal().getWidth()); 
//                            System.out.println("######arabic text location: " + ar_Marquee_Notification_Text.xProperty()); 
                            
                            
   
//    }
    
//    private void en_Animate(){
 
//                            en_Marquee_Notification_Text = new Text(en_Marquee_Notification_string);
                            
//                            en_Marquee_Notification_Text.setX(960);
                            
                    //        text.setStroke(Color.WHITESMOKE);
                    //        text.setStrokeWidth(0.4);
                            
        
        
//                            en_timeline.setCycleCount(Timeline.INDEFINITE);
//                            final KeyValue en_keyvalue = new KeyValue(en_Marquee_Notification_Text.xProperty(), -en_Marquee_Notification_Text.getBoundsInLocal().getWidth());
//                            final KeyFrame en_keyframe = new KeyFrame(Duration.millis(60000), en_keyvalue);
//                            en_timeline.getKeyFrames().add(en_keyframe);
//                            en_timeline.play();

//                             ft_en = new FadeTransition(Duration.millis(2000));
//                            ft_en.playFromStart();
                            
                            
//                            en_Marquee_Notification_Text_XPos = 1920/2 - en_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2;
//                            en_Marquee_Notification_Text_XPos = 1920 - (en_Marquee_Notification_Text.getBoundsInLocal().getWidth()/2);
                            
//                            System.out.println("######english text width: " + en_Marquee_Notification_Text.getBoundsInLocal().getWidth()); 
//                            System.out.println("######english text location: " + en_Marquee_Notification_Text.xProperty()); 
   
//    }
    
    
public static void main(String[] args) {
    launch(args);
    System.exit(0);
}

public void play_athan() throws Exception{  
    
        
        
        DateTime_now = new DateTime();    
        Calendar_now = Calendar.getInstance();
        Calendar_now.setTime(new Date());
        date_now = new Date();
        Calendar_now.set(Calendar.MILLISECOND, 0);
        Calendar_now.set(Calendar.SECOND, 0);
        
        
        
//        hour_in_hour_Label = new SimpleDateFormat("hh").format(Calendar_now.getTime());
//        minute_in_minute_Label = new SimpleDateFormat(":mm").format(Calendar_now.getTime());
//        clock_minute = Calendar_now.get(Calendar.MINUTE);
//        
//        if(clock_minute != old_clock_minute)
//        {
//            old_clock_minute = clock_minute;
//            hour_Label.setText(hour_in_hour_Label);
//            minute_Label.setText(minute_in_minute_Label);
//            date = new SimpleDateFormat("EEEE, d MMMM").format(Calendar_now.getTime());
//            date_Label.setText(date);
//            
//        }
        
//       SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//	String dateInString = "21-07-2014 22:05:56";
//	Date date2 = sdf.parse(dateInString);
//        fajr_diffsec = (int) ((date2.getTime() - date_now.getTime() ) / (1000));
        
        
        fajr_diffsec = (int) ((fajr_begins_time.getTime() - date_now.getTime() ) / (1000));
        
//        System.out.println("difference between seconds: " + fajr_diffsec); 

        if(abs(fajr_diffsec) < 61) //fajr_begins_time
        {
            count_down = true;
            fajr_diffsec_dec = fajr_diffsec/10;
            fajr_diffsec_sin = fajr_diffsec - fajr_diffsec_dec*10;
            
            if(show_friday)
            {
                fajr_hourLeft.setId("hourLeft-countdown_friday_row");
                fajr_hourRight.setId("hourLeft-countdown_friday_row");
                fajr_minLeft.setId("hourLeft-countdown_friday_row");
                fajr_minRight.setId("hourLeft-countdown_friday_row");
            
            }
            
            else
            {
                fajr_hourLeft.setId("hourLeft-countdown");
                fajr_hourRight.setId("hourLeft-countdown");
                fajr_minLeft.setId("hourLeft-countdown");
                fajr_minRight.setId("hourLeft-countdown");
                
                
            }
            fajr_hourLeft.setText("-");
            fajr_hourRight.setText("0");
            fajr_minLeft.setText(Integer.toString(fajr_diffsec_dec));
            fajr_minRight.setText(Integer.toString(fajr_diffsec_sin));
        }
        if(fajr_diffsec < 0 && count_down) //fajr_begins_time
        {
            count_down = false;
//            dateFormat = new SimpleDateFormat("hh:mm");
            fajrdate = fajr_cal.getTime();
            formattedDateTime = dateFormat.format(fajrdate);
            
            if(show_friday)
            {
                fajr_hourLeft.setId("hourLeft_friday_row");
                fajr_hourRight.setId("hourLeft_friday_row");
                fajr_minLeft.setId("hourLeft_friday_row");
                fajr_minRight.setId("hourLeft_friday_row");
            }
            
            else
            {
                fajr_hourLeft.setId("hourLeft");
                fajr_hourRight.setId("hourLeft");
                fajr_minLeft.setId("hourLeft");
                fajr_minRight.setId("hourLeft");
                
            }
            
            fajr_hourLeft.setText(formattedDateTime.substring(0, 1));
            fajr_hourRight.setText(formattedDateTime.substring(1, 2));
            fajr_minLeft.setText(formattedDateTime.substring(3, 4));
            fajr_minRight.setText(formattedDateTime.substring(4, 5));
        }
        
//        maghrib_diffsec = (int) ((date2.getTime() - date_now.getTime() ) / (1000));
        
        maghrib_diffsec = (int) ((maghrib_begins_time.getTime() - date_now.getTime() ) / (1000));
//        System.out.println("difference between seconds: " + maghrib_diffsec); 

        if(abs(maghrib_diffsec) < 61) //fajr_begins_time
        {
            count_down = true;
            maghrib_diffsec_dec = maghrib_diffsec/10;
            maghrib_diffsec_sin = maghrib_diffsec - maghrib_diffsec_dec*10;


//            
            if(show_friday)
            {
                maghrib_hourLeft.setId("hourLeft-countdown_friday_row");
                maghrib_hourRight.setId("hourLeft-countdown_friday_row");
                maghrib_minLeft.setId("hourLeft-countdown_friday_row");
                maghrib_minRight.setId("hourLeft-countdown_friday_row");
            
            }
            
            else
            {
                maghrib_hourLeft.setId("hourLeft-countdown");
                maghrib_hourRight.setId("hourLeft-countdown");
                maghrib_minLeft.setId("hourLeft-countdown");
                maghrib_minRight.setId("hourLeft-countdown");
                
                
            }
            
            maghrib_hourLeft.setText("-");
            maghrib_hourRight.setText("0");
            maghrib_minLeft.setText(Integer.toString(maghrib_diffsec_dec));
            maghrib_minRight.setText(Integer.toString(maghrib_diffsec_sin));
        }
        
        if(maghrib_diffsec < 0 && count_down) //fajr_begins_time
        {
            count_down = false;

            maghribdate = maghrib_cal.getTime();
            formattedDateTime = dateFormat.format(maghribdate);
            
            
            
            if(show_friday)
            {
                maghrib_hourLeft.setId("hourLeft_friday_row");
                maghrib_hourRight.setId("hourLeft_friday_row");
                maghrib_minLeft.setId("hourLeft_friday_row");
                maghrib_minRight.setId("hourLeft_friday_row");
            
            }
            
            else
            {
                maghrib_hourLeft.setId("hourLeft");
                maghrib_hourRight.setId("hourLeft");
                maghrib_minLeft.setId("hourLeft");
                maghrib_minRight.setId("hourLeft");
                
                
            }
                
            maghrib_hourLeft.setText(formattedDateTime.substring(0, 1));
            maghrib_hourRight.setText(formattedDateTime.substring(1, 2));
            maghrib_minLeft.setText(formattedDateTime.substring(3, 4));
            maghrib_minRight.setText(formattedDateTime.substring(4, 5));
        }
        
//        System.out.println("abs difference between seconds: " + abs(maghrib_diffsec));
        
        //==prayer alarms =================================================================================
       
//        URL url = this.getClass().getClassLoader().getResource("Audio/athan1.wav");
//        AudioFormat adFormat = getAudioFormat();
//        Clip clip = AudioSystem.getClip();
//        AudioInputStream ais = AudioSystem.getAudioInputStream( url );
        
        
//        ProcessBuilder processBuilder_Athan = new ProcessBuilder("bash", "-c", "mpg123 /home/pi/prayertime/Audio/athan1.mp3");
//        ProcessBuilder processBuilder_Duha = new ProcessBuilder("bash", "-c", "mpg123 /home/pi/prayertime/Audio/duha.mp3");
//        ProcessBuilder processBuilder_Tvon = new ProcessBuilder("bash", "-c", "echo \"as\" | cec-client -d 1 -s \"standby 0\" RPI");

        
//                            try {
//                                Process process = processBuilder.start();                                
//                            } catch (IOException ex) {
//                                logger.warn("Unexpected error", e);
//                            }
                            
        
        
//        URL url = this.getClass().getClassLoader().getResource("Audio/athan1.wav");
//        AudioInputStream ais = AudioSystem.getAudioInputStream(url); 
//        AudioFormat littleEndianFormat = getAudioFormat();
//        AudioInputStream converted = AudioSystem.getAudioInputStream(littleEndianFormat, ais); 
//        Clip clip = AudioSystem.getClip();
        
//        ProcessBuilder processBuilder_Tvon = new ProcessBuilder("bash", "-c", "echo \"as\" | cec-client -d 1 -s \"standby 0\" RPI");
//clip.open(converted);
//            clip.start();
//        Calendar_now.set(Calendar.MILLISECOND, 0);
//        Calendar_now.set(Calendar.SECOND, 0);
        
        if (duha_cal.equals(Calendar_now) && duha_athan_enable) 
        {
            if(moon_calcs_display) {Moonpane.setVisible(true);}
            if(moon_calcs_display) {Sunrisepane.setVisible(false);}
            duha_athan_enable = false;
            System.out.println("Duha Time");
//            String image = JavaFXApplication4.class.getResource("/Images/sunrise.png").toExternalForm();
//            Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-image-repeat: repeat; -fx-background-size: 1080 1920;-fx-background-position: bottom left;");
//            sensor_lastTimerCall = System.nanoTime();
//            sensorLow = true;
            
            try {Process process = processBuilder_Tvon.start(); hdmiOn = true;} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
            TimeUnit.SECONDS.sleep(3);
            try {Process process = processBuilder_Duha.start();} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
        }

        else if (fajr_cal.equals(Calendar_now) && fajr_athan_enable) 
        {
            
            
            if(moon_calcs_display) {Moonpane.setVisible(false);}
            if(moon_calcs_display) {Sunrisepane.setVisible(true);}
            
            fajr_prayer_In_Progress_notification = true;
            fajr_athan_enable = false;
            System.out.println("fajr Time");
//            clip.open(converted);
//            clip.start();
            
            sensor_lastTimerCall = System.nanoTime();
            sensorLow = true;
            try {Process process = processBuilder_Tvon.start(); hdmiOn = true;} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
            TimeUnit.SECONDS.sleep(3);
            try {Process process = processBuilder_Athan.start();} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
        }
        
        
         else if (friday_jamaat_cal.equals(Calendar_now) && !friday_jamaat_cam_activated && dayofweek_int == 6 ) 
        {
            friday_jamaat_cam_activated = true;
            System.out.println("Switching to Camera...Friday Prayer");
//            ProcessBuilder processBuilder_camera_on = new ProcessBuilder("bash", "-c", "raspistill -vf -p '25,12,670,480'  -t 5400000 -tl 200000 -w 640 -h 400 -o cam2.jpg");
            try {Process process = processBuilder_camera_on.start(); } 
            catch (IOException e) {logger.warn("Unexpected error", e);}
            camera = true;                              
                 
                 
                 
        }    
         
         else if (friday_plus30_cal.equals(Calendar_now) && friday_jamaat_cam_activated && dayofweek_int == 6) 
        {
            friday_jamaat_cam_activated = false;
            sensor1_lastTimerCall = System.nanoTime();
//            camera = false;
//            System.out.println("Manually Switching back to App...");
//            ProcessBuilder processBuilder_camera_off = new ProcessBuilder("bash", "-c", "sudo pkill raspistill");
//            try {Process process = processBuilder_camera_off.start(); } 
//            catch (IOException e) {logger.warn("Unexpected error", e);}                             
                 
                 
                 
        }    
                
        else if (zuhr_cal.equals(Calendar_now) && zuhr_athan_enable) 
        {
            zuhr_prayer_In_Progress_notification = true;
            zuhr_athan_enable = false;
            System.out.println("zuhr Time");
//            clip.open(converted);
//            clip.start();
            sensor_lastTimerCall = System.nanoTime();
            sensorLow = true;
            try {Process process = processBuilder_Tvon.start(); hdmiOn = true;} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
            TimeUnit.SECONDS.sleep(3);
            try {Process process = processBuilder_Athan.start();} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
        }        

        else if (asr_cal.equals(Calendar_now) && asr_athan_enable) 
        {
            asr_prayer_In_Progress_notification = true;
            asr_athan_enable = false;
            System.out.println("asr Time");
            sensor_lastTimerCall = System.nanoTime();
            sensorLow = true;
            try {Process process = processBuilder_Tvon.start(); hdmiOn = true;} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
            TimeUnit.SECONDS.sleep(3);
            try {Process process = processBuilder_Athan.start();} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
        } 
        
        else if (maghrib_cal.equals(Calendar_now) && maghrib_athan_enable) 
        {
            maghrib_prayer_In_Progress_notification = true;
            maghrib_athan_enable = false;
            System.out.println("maghrib Time");
            sensor_lastTimerCall = System.nanoTime();
            sensorLow = true;
            try {Process process = processBuilder_Tvon.start(); hdmiOn = true;} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
            TimeUnit.SECONDS.sleep(3);
            try {Process process = processBuilder_Athan.start();} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
//            String image = JavaFXApplication4.class.getResource("/Images/wallpaper_sunset.jpg").toExternalForm();
//            Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-image-repeat: repeat; -fx-background-size: 1080 1920;-fx-background-position: bottom left;");  
        } 
        
        else if (isha_cal.equals(Calendar_now) && isha_athan_enable) 
        {
            isha_prayer_In_Progress_notification = true;
            isha_athan_enable = false;
            System.out.println("isha Time");
            sensor_lastTimerCall = System.nanoTime();
            sensorLow = true;
            try {Process process = processBuilder_Tvon.start(); hdmiOn = true;} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
            TimeUnit.SECONDS.sleep(3);
            try {Process process = processBuilder_Athan.start();} 
            catch (IOException e) {logger.warn("Unexpected error", e);}
        }      
        
        
//move here athan player********************************************
    
    
 }   
    
public void update_labels() throws Exception{
    
        DateTime_now = new DateTime();    
        Calendar_now = Calendar.getInstance();
        Calendar_now.setTime(new Date());
        Calendar_now.set(Calendar.MILLISECOND, 0);
        Calendar_now.set(Calendar.SECOND, 0);
        
//        System.out.println("Translating Labels");
        
//==Update Clock============================================================        
        
//        hour_in_hour_Label = new SimpleDateFormat("hh").format(Calendar_now.getTime());
//        hour_Label.setText(hour_in_hour_Label);
//        minute_in_minute_Label = new SimpleDateFormat(":mm").format(Calendar_now.getTime());
//        minute_Label.setText(minute_in_minute_Label);
//        date = new SimpleDateFormat("EEEE, d MMMM").format(Calendar_now.getTime());
//        date_Label.setText(date);

//==Translate labels============================================================  
        
        if (arabic)
        {
//            System.out.println("arabic");
//            facebook_Label.setVisible(false);
            arabic = false;
            english = true;
            if(facebook_Label_visible){facebook_Label.setVisible(true); facebook_image_fade_out.playFromStart();}
            athan_Label_ar.setVisible(false);
            athan_Label_eng.setVisible(true);
            jamaat_Label_ar.setVisible(false);
            jamaat_Label_eng.setVisible(true);
            friday_Label_ar.setVisible(false);
            friday_Label_eng.setVisible(true);
            fajr_Label_ar.setVisible(false);
            fajr_Label_eng.setVisible(true);
            zuhr_Label_ar.setVisible(false);
            zuhr_Label_eng.setVisible(true);
            asr_Label_ar.setVisible(false);
            asr_Label_eng.setVisible(true);
            maghrib_Label_ar.setVisible(false);
            maghrib_Label_eng.setVisible(true);
            isha_Label_ar.setVisible(false);
            isha_Label_eng.setVisible(true);
            
            
            
            Sunrise_Date_Label.setId("sunrise-text-english");
            Sunrise_Date_Label.setWrapText(true);
            Sunrisepane.setHalignment(Sunrise_Date_Label,HPos.RIGHT);
            Sunrise_Date_Label.setText("Sunrise time:  " + String.format("%d", sunrise_time.getHours()) +  ":" + String.format("%02d", sunrise_time.getMinutes()));

                                
            if (hadith_Label_visible)
            {
                
//                System.out.println("en hadith_Label_visible");

                
                hadith_Label.setVisible(true);
                hadith_Label.setText(translated_hadith);
                hadith_Label.setId("hadith-text-english");
                hadithPane.setValignment(hadith_Label,VPos.TOP);
                ar_moon_hadith_Label_L1.setVisible(false);
                ar_moon_hadith_Label_L2.setVisible(false);
                en_moon_hadith_Label_L1.setVisible(false);
                en_moon_hadith_Label_L2.setVisible(false);
                
                en_moon_hadith_Label_L2.setText("");
                ar_moon_hadith_Label_L2.setText("");
                ar_moon_hadith_Label_L2.setMinHeight(0);
                en_moon_hadith_Label_L2.setMinHeight(0);
                hadith_Label.setMinHeight(0);
 
            }
            
            if (moon_hadith_Label_visible)
            {

//                System.out.println("en moon_hadith_Label_visible");
                en_moon_hadith_Label_L1.setVisible(true);
                en_moon_hadith_Label_L1.setText(en_full_moon_hadith);
                en_moon_hadith_Label_L1.setId("en_moon-notification-text1");
//                en_moon_hadith_Label_L1.setTranslateY(5);
                
                en_moon_hadith_Label_L2.setVisible(true);
                en_moon_hadith_Label_L2.setText(en_moon_notification);
                en_moon_hadith_Label_L2.setId("en_moon-notification-text2");
                hadithPane.setHalignment(en_moon_hadith_Label_L2,HPos.LEFT);

                
                ar_moon_hadith_Label_L1.setVisible(false);
                ar_moon_hadith_Label_L1.setText("");
                ar_moon_hadith_Label_L1.setMinHeight(0);
                hadith_Label.setVisible(false);
                hadith_Label.setMinHeight(0);
                hadith_Label.setText("");
                ar_moon_hadith_Label_L2.setVisible(false);
//                divider1_Label.setMinHeight(50);
            }
            
            

            if (!facebook_Label_visible)
            {
                facebook_Label.setVisible(false);
                facebook_Label.setText("");
//                divider1_Label.setVisible(false);
                
            }                     
            
            
            
            if (!athan_Change_Label_visible | !notification_Marquee_visible)
            {
                
                text_Box.setVisible(false);
                en_Marquee_Notification_Text.setVisible(false);
                ar_Marquee_Notification_Text.setVisible(false);

            }
            
            if (athan_Change_Label_visible | notification_Marquee_visible)
            {
                
                if (!camera)
                {
                    text_Box.setVisible(true);
                    ar_Marquee_Notification_Text.setVisible(false);
//                    ar_timeline.stop();
//                    en_Marquee_Notification_Text_XPos = 320;
                    ft_en.playFromStart();
                    ft_ar.stop();
//                    en_Animate();
                    en_Marquee_Notification_Text.setVisible(true);
                }

            }
                        
            
            
            
            
            if(newMoon != null && fullMoon != null)
            {
                if (newMoon.before(fullMoon))
                {
                    Calendar_now.setTime(newMoon);
                    int day = Calendar_now.get(Calendar.DAY_OF_MONTH);
                    String dayStr = day + suffixes[day];
                    DateTimeComparator comparator = DateTimeComparator.getTimeOnlyInstance();

                    Days d = Days.daysBetween(new DateMidnight(DateTime_now), new DateMidnight(newMoon));
                    int days_Between_Now_Newmoon = d.getDays();

                    if ( days_Between_Now_Newmoon <= 7 && days_Between_Now_Newmoon >1)
                    {
                        String newMoon_date_en = new SimpleDateFormat("EEEE").format(newMoon);
                        String newMoon_date_en1 = new SimpleDateFormat("' of ' MMMM").format(newMoon);

                        Moon_Date_Label.setId("moon-text-english");

                        if (comparator.compare(newMoon, maghrib_cal)>0){Moon_Date_Label.setText("New moon is on next\n" + newMoon_date_en + " night "  + dayStr + newMoon_date_en1);}
                        else{Moon_Date_Label.setText("New moon is on next\n" + newMoon_date_en + " "  + dayStr  + newMoon_date_en1);}

                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);

                    }

                    else if ( days_Between_Now_Newmoon == 0)
                    {
                        Moon_Date_Label.setId("moon-text-english");

                        if (comparator.compare(newMoon, maghrib_cal)>0){Moon_Date_Label.setText("The moon is new tonight" );}
                        else{Moon_Date_Label.setText("The moon is new today" );}

                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}
                    }

                    else if ( days_Between_Now_Newmoon >0 && days_Between_Now_Newmoon <=1 )
                    {
                        Moon_Date_Label.setId("moon-text-english");
                        if (comparator.compare(newMoon, maghrib_cal)>0){Moon_Date_Label.setText("New moon is on\n"+ "tomorrow night");}
                        else{Moon_Date_Label.setText("New moon is on\n"+ "tomorrow");}
                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}
                    }

                    else if ( days_Between_Now_Newmoon <10 && days_Between_Now_Newmoon > 7)
                    {
                        String newMoon_date_en = new SimpleDateFormat("EEEE").format(newMoon);
                        String newMoon_date_en1 = new SimpleDateFormat("' of ' MMMM").format(newMoon);
                        Moon_Date_Label.setId("moon-text-english");
                        Moon_Date_Label.setText("New moon is on\n" + newMoon_date_en + " "  + dayStr  + newMoon_date_en1);
                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}
                    }

                    else 
                    {            
                        String newMoon_date_en = new SimpleDateFormat("EEEE").format(newMoon);
                        String newMoon_date_en1 = new SimpleDateFormat("' of ' MMMM").format(newMoon);
                        Moon_Date_Label.setId("moon-text-english");
                        Moon_Date_Label.setText("Next new Moon is on\n" + newMoon_date_en  + " "  + dayStr  + newMoon_date_en1);
                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}

        //            String image = JavaFXApplication4.class.getResource("wallpaper4.jpg").toExternalForm();
        //            Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-repeat: stretch; -fx-background-size: 650 1180;-fx-background-position: top left;");
                    }


                }

                else
                {
                    Calendar_now.setTime(fullMoon);
                    int day = Calendar_now.get(Calendar.DAY_OF_MONTH);
                    String dayStr = day + suffixes[day];
                    DateTimeComparator comparator = DateTimeComparator.getTimeOnlyInstance();
                    Days d = Days.daysBetween(new DateMidnight(DateTime_now), new DateMidnight(fullMoon));
                    int days_Between_Now_Fullmoon = d.getDays();

                    if ( days_Between_Now_Fullmoon <= 7 && days_Between_Now_Fullmoon >1)
                    {
                        String FullMoon_date_en = new SimpleDateFormat("EEEE").format(fullMoon);
                        String FullMoon_date_en1 = new SimpleDateFormat("' of ' MMMM").format(fullMoon);

                        Moon_Date_Label.setId("moon-text-english");

                        if (comparator.compare(fullMoon, maghrib_cal)>0){Moon_Date_Label.setText("Full moon is on next\n" + FullMoon_date_en + " night");}
                        else{Moon_Date_Label.setText("Full moon is on next\n" + FullMoon_date_en + " "  + dayStr  + FullMoon_date_en1);}

                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}
                    }

                    else if ( days_Between_Now_Fullmoon == 0)
                    {
                        Moon_Date_Label.setId("moon-text-english");

                        if (comparator.compare(fullMoon, maghrib_cal)>0){Moon_Date_Label.setText("The moon is full tonight" );}
                        else{Moon_Date_Label.setText("The moon is full today" );}

                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}
                    }

                    else if ( days_Between_Now_Fullmoon >0 && days_Between_Now_Fullmoon <=1 )
                    {
                        Moon_Date_Label.setId("moon-text-english");
                        if (comparator.compare(fullMoon, maghrib_cal)>0){Moon_Date_Label.setText("Full moon is on\n"+ "tomorrow night");}
                        else{Moon_Date_Label.setText("Full moon is on\n"+ "tomorrow");}
                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}
                    }

                    else if ( days_Between_Now_Fullmoon <10 && days_Between_Now_Fullmoon > 7)
                    {
                        String FullMoon_date_en = new SimpleDateFormat("EEEE").format(fullMoon);
                        String FullMoon_date_en1 = new SimpleDateFormat("' of ' MMMM").format(fullMoon);
                        Moon_Date_Label.setId("moon-text-english");
                        Moon_Date_Label.setText("Full moon is on\n" + FullMoon_date_en + " "  + dayStr  + FullMoon_date_en1);
                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}
                    }

                    else 
                    {            
                        String FullMoon_date_en = new SimpleDateFormat("EEEE").format(fullMoon);
                        String FullMoon_date_en1 = new SimpleDateFormat("' of ' MMMM").format(fullMoon);
                        Moon_Date_Label.setId("moon-text-english");
                        Moon_Date_Label.setText("Next Full Moon is on\n" + FullMoon_date_en  + " "  + dayStr  + FullMoon_date_en1);
                        Moonpane.setHalignment(Moon_Date_Label,HPos.LEFT);
//                        arabic = false;
//                        if (facebook_Label_visible && !facebook_turn && arabic)
//                        {
//                            facebook_turn = true;
//
//                        }
//                        else {arabic = false;}

        //            String image = JavaFXApplication4.class.getResource("wallpaper4.jpg").toExternalForm();
        //            Mainpane.setStyle("-fx-background-image: url('" + image + "'); -fx-background-repeat: stretch; -fx-background-size: 650 1180;-fx-background-position: top left;");
                    }
                }
            }
        }

        else if (english)
        { 
//            System.out.println("english");
            english = false;
            
            if(facebook_Label_visible){facebook_turn = true;}
            else{arabic = true;}
            
            
//            facebook_Label.setVisible(false);
            
            athan_Label_eng.setVisible(false);
            athan_Label_ar.setVisible(true);
            jamaat_Label_eng.setVisible(false);
            jamaat_Label_ar.setVisible(true);
            friday_Label_eng.setVisible(false);
            friday_Label_ar.setVisible(true);
            fajr_Label_ar.setVisible(true);
            fajr_Label_eng.setVisible(false);
            zuhr_Label_ar.setVisible(true);
            zuhr_Label_eng.setVisible(false);
            asr_Label_ar.setVisible(true);
            asr_Label_eng.setVisible(false);
            maghrib_Label_ar.setVisible(true);
            maghrib_Label_eng.setVisible(false);
            isha_Label_ar.setVisible(true);
            isha_Label_eng.setVisible(false);
            
            Sunrise_Date_Label.setId("sunrise-text-arabic");
            Sunrise_Date_Label.setWrapText(true);
            Sunrisepane.setHalignment(Sunrise_Date_Label,HPos.RIGHT);
            Sunrise_Date_Label.setText("وقت الشروق " + ": " + String.format("%d", sunrise_time.getHours()) +  ":" + String.format("%02d", sunrise_time.getMinutes()));
//            System.out.println("sunrise time: " + String.format("%d", sunrise_time.getHours()) +  ":" + String.format("%02d", sunrise_time.getMinutes()));


            hadithPane.setHalignment(athan_Change_Label_L1,HPos.RIGHT);
            
            if (hadith_Label_visible)
            {
//                System.out.println("ar hadith_Label_visible");
                hadith_Label.setVisible(true);
                hadith_Label.setText("");
                hadith_Label.setMinHeight(0);
                hadith_Label.setText(hadith);
                hadith_Label.setId("hadith-text-arabic");
                ar_moon_hadith_Label_L1.setVisible(false);
                ar_moon_hadith_Label_L2.setVisible(false);
                en_moon_hadith_Label_L1.setVisible(false);
                en_moon_hadith_Label_L2.setVisible(false);
                en_moon_hadith_Label_L2.setText("");
                ar_moon_hadith_Label_L2.setText("");
                ar_moon_hadith_Label_L2.setMinHeight(0);
                en_moon_hadith_Label_L2.setMinHeight(0);
                
            }
            
            if (moon_hadith_Label_visible)
            {
//                System.out.println("ar moon hadith_Label_visible");
                ar_moon_hadith_Label_L1.setVisible(true);
                ar_moon_hadith_Label_L1.setText(ar_full_moon_hadith);
                ar_moon_hadith_Label_L1.setId("ar_moon-notification-text1");
                hadithPane.setHalignment(ar_moon_hadith_Label_L1,HPos.RIGHT);
//                ar_moon_hadith_Label_L1.setTranslateY(5);
                
                ar_moon_hadith_Label_L2.setVisible(true);
                ar_moon_hadith_Label_L2.setText(ar_moon_notification);
                ar_moon_hadith_Label_L2.setId("ar_moon-notification-text2");
                hadithPane.setHalignment(ar_moon_hadith_Label_L2,HPos.RIGHT);
                
//                facebook_Label.setVisible(false);
//                facebook_Label.setText("");
//                facebook_Label.setGraphic(null);
//                facebook_Label.setMinHeight(0);
                en_moon_hadith_Label_L1.setVisible(false);
                en_moon_hadith_Label_L1.setText("");
                en_moon_hadith_Label_L1.setMinHeight(0);
                hadith_Label.setVisible(false);
                hadith_Label.setMinHeight(0);
                hadith_Label.setText("");
                en_moon_hadith_Label_L2.setVisible(false);
//                divider1_Label.setMinHeight(50);
            }
            
            
            if (!athan_Change_Label_visible | !notification_Marquee_visible)
            {

                text_Box.setVisible(false);
                en_Marquee_Notification_Text.setVisible(false);
                ar_Marquee_Notification_Text.setVisible(false);
//                athan_Change_Label_L1.setVisible(false);
//                athan_Change_Label_L2.setVisible(false);
//                athan_Change_Label_L1.setMaxHeight(0);
//                athan_Change_Label_L2.setMaxHeight(0);
//                divider1_Label.setVisible(false);
//                divider1_Label.setMaxHeight(0);
            }
            
            if (athan_Change_Label_visible | notification_Marquee_visible)
            {
                
                if (!camera)
                {
                    text_Box.setVisible(true);
//                    en_timeline.stop();
//                    ft_en.stop();
                    ft_ar.playFromStart();
                    ft_en.stop();
                    
                    
//                    ar_Marquee_Notification_Text_XPos = -320;
//                    ar_Animate();
                    ar_Marquee_Notification_Text.setVisible(true);
                    en_Marquee_Notification_Text.setVisible(false);
                }
                
//                athan_Change_Label_L1.setVisible(true);
//                athan_Change_Label_L2.setVisible(true);
//                athan_Change_Label_L1.setMaxHeight(200);
//                athan_Change_Label_L2.setMaxHeight(200);
//                divider1_Label.setVisible(true);
//                divider1_Label.setMaxHeight(50);
//                athan_Change_Label_L1.setText(ar_notification_Msg_Lines[0]);
//                athan_Change_Label_L1.setId("ar_athan-change-ar_notification_Msg_Linestext");
//                athan_Change_Label_L2.setText(ar_notification_Msg_Lines[1]);
//                athan_Change_Label_L2.setId("ar_athan-change-textL2");
//                hadithPane.setHalignment(athan_Change_Label_L2,HPos.RIGHT);

//                facebook_Label.setVisible(false);
//                facebook_Label.setText("");
//                facebook_Label.setGraphic(null);
//                facebook_Label.setMinHeight(0);
            }
            
            
            if(newMoon != null && fullMoon != null)
            {
                if (newMoon.before(fullMoon))
                {
                        Days d = Days.daysBetween(new DateMidnight(DateTime_now), new DateMidnight(newMoon));
                        int days_Between_Now_Newmoon = d.getDays();
                        DateTimeComparator comparator = DateTimeComparator.getTimeOnlyInstance();

                        if ( days_Between_Now_Newmoon <= 7 && days_Between_Now_Newmoon >1)
                        {
                            String newMoon_date_ar = new SimpleDateFormat("' 'EEEE", new Locale("ar")).format(newMoon);
                            String newMoon_date_ar1 = new SimpleDateFormat("d MMMM", new Locale("ar")).format(newMoon);

                            if (comparator.compare(newMoon, maghrib_cal)>0){ labeconv = "سيظهر الهلال  ليلة\n" + newMoon_date_ar + " القادم";}
                            else{ labeconv = "سيظهر الهلال يوم\n" + newMoon_date_ar + " القادم" ;}

                            StringBuilder builder = new StringBuilder();
                            for(int i =0;i<labeconv.length();i++)
                            {
                                if(Character.isDigit(labeconv.charAt(i)))
                                {
                                    builder.append(arabicChars[(int)(labeconv.charAt(i))-48]);
                                }
                                else
                                {
                                    builder.append(labeconv.charAt(i));
                                }
                            }

                            Moon_Date_Label.setId("moon-text-arabic");
                            Moon_Date_Label.setText(builder.toString());
                            Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                            arabic = true;

                        }

                        else if ( days_Between_Now_Newmoon >0 && days_Between_Now_Newmoon <=1 )
                        {
                            Moon_Date_Label.setId("moon-text-arabic");
                            if (comparator.compare(newMoon, maghrib_cal)>0){ Moon_Date_Label.setText("سيظهر الهلال ليلة الغذٍٍُِِِ" );}
                            else{Moon_Date_Label.setText("سيظهر الهلال غدا" );}
                            Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                            arabic = true;
                        }

                        else if ( days_Between_Now_Newmoon == 0)
                        {
                            Moon_Date_Label.setId("moon-text-arabic");
                            if (comparator.compare(newMoon, maghrib_cal)>0){Moon_Date_Label.setText("سيظهر الهلال ليلة اليوم" );}
                            else{Moon_Date_Label.setText("سيظهر الهلال اليوم " );}
                            Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                            arabic = true;
                        }

                        else if ( days_Between_Now_Newmoon <10 && days_Between_Now_Newmoon > 7)
                        {
                            String newMoon_date_ar = new SimpleDateFormat("' 'EEEE", new Locale("ar")).format(newMoon);
                            String newMoon_date_ar1 = new SimpleDateFormat("d MMMM", new Locale("ar")).format(newMoon);
                            labeconv = "سيظهر الهلال " + newMoon_date_ar + ", " + newMoon_date_ar1;
                            StringBuilder builder = new StringBuilder();
                            for(int i =0;i<labeconv.length();i++)
                            {
                                if(Character.isDigit(labeconv.charAt(i)))
                                {
                                    builder.append(arabicChars[(int)(labeconv.charAt(i))-48]);
                                }
                                else
                                {
                                    builder.append(labeconv.charAt(i));
                                }
                            }

                            Moon_Date_Label.setId("moon-text-arabic");
                            Moon_Date_Label.setText(builder.toString());
                            Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                            arabic = true;
                        }

                        else 
                        {            
                            String newMoon_date_ar = new SimpleDateFormat(" EEEE d MMMM", new Locale("ar")).format(newMoon);               
                            labeconv = "سيظهر الهلال يوم\n" + newMoon_date_ar;
                            StringBuilder builder = new StringBuilder();
                            for(int i =0;i<labeconv.length();i++)
                            {
                                if(Character.isDigit(labeconv.charAt(i)))
                                {
                                    builder.append(arabicChars[(int)(labeconv.charAt(i))-48]);
                                }
                                else
                                {
                                    builder.append(labeconv.charAt(i));
                                }
                            }

                            Moon_Date_Label.setId("moon-text-arabic");
                            Moon_Date_Label.setText(builder.toString());
                            Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                            arabic = true;
                        }

                }

                else
                {
                    DateTimeComparator comparator = DateTimeComparator.getTimeOnlyInstance();
                    Days d = Days.daysBetween(new DateMidnight(DateTime_now), new DateMidnight(fullMoon));
                    int days_Between_Now_Fullmoon = d.getDays();

                    if ( days_Between_Now_Fullmoon <= 7 && days_Between_Now_Fullmoon >1)
                    {
                        String FullMoon_date_ar = new SimpleDateFormat("' 'EEEE", new Locale("ar")).format(fullMoon);
                        String FullMoon_date_ar1 = new SimpleDateFormat("d MMMM", new Locale("ar")).format(fullMoon);


                        if (comparator.compare(fullMoon, maghrib_cal)>0){ labeconv = "سيكون القمر بدرا ليلة\n" + FullMoon_date_ar + " القادم" ;}
                        else{ labeconv = "سيكون القمر بدرا\n" + FullMoon_date_ar + " القادم" ;}

                        StringBuilder builder = new StringBuilder();
                        for(int i =0;i<labeconv.length();i++)
                        {
                            if(Character.isDigit(labeconv.charAt(i)))
                            {
                                builder.append(arabicChars[(int)(labeconv.charAt(i))-48]);
                            }
                            else
                            {
                                builder.append(labeconv.charAt(i));
                            }
                        }

                        Moon_Date_Label.setId("moon-text-arabic");
                        Moon_Date_Label.setText(builder.toString());
                        Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                        arabic = true;

                    }

                    else if ( days_Between_Now_Fullmoon >0 && days_Between_Now_Fullmoon <=1 )
                    {
                        Moon_Date_Label.setId("moon-text-arabic");
                        if (comparator.compare(fullMoon, maghrib_cal)>0){ Moon_Date_Label.setText("سيكون القمر بدرا ليلة الغذٍٍُِِِ" );}
                        else{Moon_Date_Label.setText("سيكون القمر بدرا غدا" );}
                        Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                        arabic = true;
                    }

                    else if ( days_Between_Now_Fullmoon == 0)
                    {
                        Moon_Date_Label.setId("moon-text-arabic");
                        if (comparator.compare(fullMoon, maghrib_cal)>0){Moon_Date_Label.setText("القمر بدر ليلة اليوم" );}
                        else{Moon_Date_Label.setText("القمر بدر اليوم " );}
                        Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                        arabic = true;
                    }

                    else if ( days_Between_Now_Fullmoon <10 && days_Between_Now_Fullmoon > 7)
                    {
                        String FullMoon_date_ar = new SimpleDateFormat("' 'EEEE", new Locale("ar")).format(fullMoon);
                        String FullMoon_date_ar1 = new SimpleDateFormat("d MMMM", new Locale("ar")).format(fullMoon);
                        labeconv = "سيكون القمر بدرا " + FullMoon_date_ar + ", " + FullMoon_date_ar1;
                        StringBuilder builder = new StringBuilder();
                        for(int i =0;i<labeconv.length();i++)
                        {
                            if(Character.isDigit(labeconv.charAt(i)))
                            {
                                builder.append(arabicChars[(int)(labeconv.charAt(i))-48]);
                            }
                            else
                            {
                                builder.append(labeconv.charAt(i));
                            }
                        }

                        Moon_Date_Label.setId("moon-text-arabic");
                        Moon_Date_Label.setText(builder.toString());
                        Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                        arabic = true;
                    }

                    else 
                    {            
                        String FullMoon_date_ar = new SimpleDateFormat(" EEEE d MMMM", new Locale("ar")).format(fullMoon);               
                        labeconv = "سيكون القمر بدرا \n" + FullMoon_date_ar;
                        StringBuilder builder = new StringBuilder();
                        for(int i =0;i<labeconv.length();i++)
                        {
                            if(Character.isDigit(labeconv.charAt(i)))
                            {
                                builder.append(arabicChars[(int)(labeconv.charAt(i))-48]);
                            }
                            else
                            {
                                builder.append(labeconv.charAt(i));
                            }
                        }

                        Moon_Date_Label.setId("moon-text-arabic");
                        Moon_Date_Label.setText(builder.toString());
                        Moonpane.setHalignment(Moon_Date_Label,HPos.RIGHT);
//                        arabic = true;
//                        facebook_turn = false;
                    }
                }
            }
        }
        
        else if (facebook_turn && facebook_Label_visible)
                {
  
//                System.out.println("facebook_Label_visible");
                    if (facebook_Label_visible_set_once)
                    { 
                        facebook_Label_visible_set_once = false;
                        
                        if(null != facebook_post && !"".equals(facebook_post))
                        {    
                            System.out.println("facebook_post label set");
                            facebook_Label.setGraphic(null);
                            facebook_Label.setText(facebook_post);
                            facebook_Label.setId("facebook-text");
                            facebook_Label.setVisible(true);
                        }

                        if(null != facebook_Post_Url && !"".equals(facebook_Post_Url))
                        {
                            System.out.println("facebook_post picture label set");
                            facebook_Label.setText("");
                            ImageView imageView = ImageViewBuilder.create().image(new Image(facebook_Post_Url)).build();  
                            Image image = new Image(facebook_Post_Url); 
    //                        System.out.print("facebookimage width:");
    //                        System.out.println(image.getWidth());
    //                        System.out.print("facebookimage height:");
    //                        System.out.println(image.getHeight());
    //                        double width = image.getWidth();
    //                        double height = image.getHeight();

                            if (image.getWidth()>image.getHeight())
                            {

                                if (orientation.equals("vertical") )
                                {



                                }    
                                else if (orientation.equals("horizontal") )
                                {
                                    imageView.setFitWidth(600);
                                    
                                }

                                else if (orientation.equals("horizontal_HD") )
                                {
                                    imageView.setFitWidth(850);
                                    
                                }
                                imageView.setPreserveRatio(true);
                                facebook_Label.setGraphic(imageView);
                                facebook_Label.setAlignment(Pos.TOP_CENTER);
//                                facebook_Label.setTranslateY(170);


                            }

                            else if (image.getHeight()>image.getWidth())
                            {
                            
                                if (orientation.equals("vertical") )
                                {



                                }    
                                else if (orientation.equals("horizontal") )
                                {
                                    imageView.setFitHeight(780);
//                                    facebook_Label.setTranslateY(-60);
                                }

                                else if (orientation.equals("horizontal_HD") )
                                {
                                    imageView.setFitHeight(675);
//                                    facebook_Label.setTranslateY(20);
                                }
                                imageView.setPreserveRatio(true);
                                facebook_Label.setGraphic(imageView);
//                                facebook_Label.setAlignment(Pos.CENTER);
                                hadithPane.setHalignment(facebook_Label,HPos.CENTER);
                                

                            }
//                            
                        }            
                            
                    }
                
                if(null != facebook_Post_Url && !"".equals(facebook_Post_Url)){facebook_image_fade_in.playFromStart();}
                
                ar_moon_hadith_Label_L1.setVisible(false);
                ar_moon_hadith_Label_L2.setVisible(false);
                en_moon_hadith_Label_L1.setVisible(false);
                en_moon_hadith_Label_L2.setVisible(false);
                hadith_Label.setVisible(false);
                facebook_turn = false;
                arabic = true;

                }
        
        
//==Days left to full moon============================================================        
 

                
        
// Jammat update=========================================================== 
        
        Date now = new Date();
//        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
//        System.out.println(fajr_jamaat_update_cal.getTime());
        if (jammat_from_database)
        {
            if ( fajr_jamaat_update_cal.equals(Calendar_now) && fajr_jamaat_update_enable )         
            {       
                fajr_jamaat_update_enable = false;
                new Thread(new Runnable() 
                {
                    public void run() 
                    {
                        try {
                            c = DBConnect.connect();
                            SQL = "select * from " +  prayertime_database +  " where DATE(date) = DATE(NOW()) + 1";
//                            SQL = "select * from prayertimes where DATE(date) = DATE(NOW()) + 1";
                            rs = c.createStatement().executeQuery(SQL);
                            while (rs.next())
                            {
                                fajr_jamaat_time =       rs.getTime("fajr_jamaat");
                            }
                            c.close();
                            fajr_jamaat = fajr_jamaat_time.toString();
                            System.out.println("fajr jamaat time updated:" + fajr_jamaat);


                            Date fajr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " " + fajr_jamaat);
                            cal.setTime(fajr_jamaat_temp);
                            cal.add(Calendar.MINUTE, 15);
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                            Date fajr_jamaat = cal.getTime();
                            fajr_jamaat_update_cal = Calendar.getInstance();
                            fajr_jamaat_update_cal.setTime(fajr_jamaat);
                            fajr_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
                            fajr_jamaat_update_cal.set(Calendar.SECOND, 0);
        //                            System.out.println(fajr_jamaat_update_cal.getTime());

                            fajr_jamaat_cal = (Calendar)fajr_jamaat_update_cal.clone();
                            fajr_jamaat_cal.add(Calendar.MINUTE, -15);

                            System.out.println("next update is on:" + fajr_jamaat_update_cal.getTime());
                            TimeUnit.MINUTES.sleep(1);
                            fajr_jamaat_update_enable = true;
                            update_prayer_labels = true;





                        } 
                        catch (SQLException e) {logger.warn("Unexpected error", e);} 
                        catch (ParseException e) {logger.warn("Unexpected error", e);} 
                        catch (InterruptedException e) {logger.warn("Unexpected error", e);}
                   }
                }).start();
            }   

            if ( asr_jamaat_update_cal.equals(Calendar_now) && asr_jamaat_update_enable )         
            {       

                asr_jamaat_update_enable = false;
                new Thread(new Runnable() 
                {
                    public void run() 
                    {
                        try {
                            c = DBConnect.connect();
                            SQL = "select * from " +  prayertime_database +  " where DATE(date) = DATE(NOW()) + 1";
//                            SQL = "select * from prayertimes where DATE(date) = DATE(NOW()) + 1";
                            rs = c.createStatement().executeQuery(SQL);
                            while (rs.next())
                            {
                                asr_jamaat_time =       rs.getTime("asr_jamaat");
                            }
                            c.close();
                            asr_jamaat = asr_jamaat_time.toString();
                            System.out.println("asr jamaat time updated:" + asr_jamaat);
                            Date asr_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " " + asr_jamaat);
                            cal.setTime(asr_jamaat_temp);
                            cal.add(Calendar.MINUTE, 15);
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                            Date asr_jamaat = cal.getTime();
                            asr_jamaat_update_cal = Calendar.getInstance();
                            asr_jamaat_update_cal.setTime(asr_jamaat);
                            asr_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
                            asr_jamaat_update_cal.set(Calendar.SECOND, 0);
        //                            System.out.println(fajr_jamaat_update_cal.getTime());

                            asr_jamaat_cal = (Calendar)asr_jamaat_update_cal.clone();
                            asr_jamaat_cal.add(Calendar.MINUTE, -15);

                            System.out.println("next update is on:" + asr_jamaat_update_cal.getTime());
                            TimeUnit.MINUTES.sleep(1);
                            asr_jamaat_update_enable = true;
                            update_prayer_labels = true;

                        } 
                        catch (SQLException e) {logger.warn("Unexpected error", e);} 
                        catch (ParseException e) {logger.warn("Unexpected error", e);} 
                        catch (InterruptedException e) {logger.warn("Unexpected error", e);}
                   }
                }).start();
            }
        
        
        
            if (maghrib_adj ==60 && maghrib_jamaat_update_cal.equals(Calendar_now) && maghrib_jamaat_update_enable )         
            {       

                maghrib_jamaat_update_enable = false;
                new Thread(new Runnable() 
                {
                    public void run() 
                    {
                        try {
                            c = DBConnect.connect();
                            SQL = "select * from " +  prayertime_database +  " where DATE(date) = DATE(NOW()) + 1";
//                            SQL = "select * from prayertimes where DATE(date) = DATE(NOW()) + 1";
                            rs = c.createStatement().executeQuery(SQL);
                            while (rs.next())
                            {
                                maghrib_jamaat_time =       rs.getTime("maghrib_jamaat");
                            }
                            c.close();
                            maghrib_jamaat = maghrib_jamaat_time.toString();
                            System.out.println("maghrib jamaat time updated:" + maghrib_jamaat);
                            Date maghrib_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " " + maghrib_jamaat);
                            cal.setTime(maghrib_jamaat_temp);
                            cal.add(Calendar.MINUTE, 15);
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                            Date maghrib_jamaat = cal.getTime();
                            maghrib_jamaat_update_cal = Calendar.getInstance();
                            maghrib_jamaat_update_cal.setTime(maghrib_jamaat);
                            maghrib_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
                            maghrib_jamaat_update_cal.set(Calendar.SECOND, 0);
        //                            System.out.println(fajr_jamaat_update_cal.getTime());

                            maghrib_jamaat_cal = (Calendar)maghrib_jamaat_update_cal.clone();
                            maghrib_jamaat_cal.add(Calendar.MINUTE, -15);

                            System.out.println("next update is on:" + maghrib_jamaat_update_cal.getTime());
                            TimeUnit.MINUTES.sleep(1);
                            maghrib_jamaat_update_enable = true;
                            update_prayer_labels = true;

                        } 
                        catch (SQLException e) {logger.warn("Unexpected error", e);} 
                        catch (ParseException e) {logger.warn("Unexpected error", e);} 
                        catch (InterruptedException e) {logger.warn("Unexpected error", e);}
                   }
                }).start();
            }
        
        
        
            if (isha_jamaat_update_cal.equals(Calendar_now) && isha_jamaat_update_enable )         
            {       
                isha_jamaat_update_enable = false;
                new Thread(new Runnable() 
                {
                    public void run() 
                    {
                        try {
                            c = DBConnect.connect();
//                            SQL = "select * from prayertimes where DATE(date) = DATE(NOW()) + 1";
                            SQL = "select * from " +  prayertime_database +  " where DATE(date) = DATE(NOW()) + 1";
                            rs = c.createStatement().executeQuery(SQL);
                            while (rs.next())
                            {
                                isha_jamaat_time =       rs.getTime("isha_jamaat");
                            }
                            c.close();
                            isha_jamaat = isha_jamaat_time.toString();
                            System.out.println("isha jamaat time updated:" + isha_jamaat);
                            Date isha_jamaat_temp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " " + isha_jamaat);
                            cal.setTime(isha_jamaat_temp);
                            cal.add(Calendar.MINUTE, 15);
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                            Date isha_jamaat = cal.getTime();
                            isha_jamaat_update_cal = Calendar.getInstance();
                            isha_jamaat_update_cal.setTime(isha_jamaat);
                            isha_jamaat_update_cal.set(Calendar.MILLISECOND, 0);
                            isha_jamaat_update_cal.set(Calendar.SECOND, 0);
        //                            System.out.println(fajr_jamaat_update_cal.getTime());

                            isha_jamaat_cal = (Calendar)isha_jamaat_update_cal.clone();
                            isha_jamaat_cal.add(Calendar.MINUTE, -15);

                            System.out.println("next update is on:" + isha_jamaat_update_cal.getTime());
                            TimeUnit.MINUTES.sleep(1);
                            isha_jamaat_update_enable = true;
                            update_prayer_labels = true;

                        } 
                        catch (SQLException e) {logger.warn("Unexpected error", e);} 
                        catch (ParseException e) {logger.warn("Unexpected error", e);} 
                        catch (InterruptedException e) {logger.warn("Unexpected error", e);}
                   }
                }).start();
            }
        }
 
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
////        Date preDefineTime=formatter.parse("10:00");
//        long additionMin=15*60*1000;
//        System.out.println(formatter.format(sunrise));
//        System.out.println(formatter.format(sunrise.getTime()+additionMin));
       
//==Update Prayer time Labels==========================================================        
        
        if (update_prayer_labels && !count_down) 
        {
            update_prayer_labels = false;

            fajrdate = fajr_cal.getTime();
            formattedDateTime = dateFormat.format(fajrdate);
            
            fajr_hourLeft.setText(formattedDateTime.substring(0, 1));
            fajr_hourRight.setText(formattedDateTime.substring(1, 2));
            fajr_minLeft.setText(formattedDateTime.substring(3, 4));
            fajr_minRight.setText(formattedDateTime.substring(4, 5));
            
//            sunrise_hourLeft.setText(sunrise_time.toString().substring(11, 12));
//            sunrise_hourRight.setText(sunrise_time.toString().substring(12, 13));
//            sunrise_minLeft.setText(sunrise_time.toString().substring(14, 15));
//            sunrise_minRight.setText(sunrise_time.toString().substring(15, 16));

            zuhrdate = zuhr_cal.getTime();
            formattedDateTime = dateFormat.format(zuhrdate);
            
            zuhr_hourLeft.setText(formattedDateTime.substring(0, 1));
            zuhr_hourRight.setText(formattedDateTime.substring(1, 2));
            zuhr_minLeft.setText(formattedDateTime.substring(3, 4));
            zuhr_minRight.setText(formattedDateTime.substring(4, 5));
             
            asrdate = asr_cal.getTime();
            formattedDateTime = dateFormat.format(asrdate);
            
            asr_hourLeft.setText(formattedDateTime.substring(0, 1));
            asr_hourRight.setText(formattedDateTime.substring(1, 2));
            asr_minLeft.setText(formattedDateTime.substring(3, 4));
            asr_minRight.setText(formattedDateTime.substring(4, 5));
            
            maghribdate = maghrib_cal.getTime();
            formattedDateTime = dateFormat.format(maghribdate);
            
            maghrib_hourLeft.setText(formattedDateTime.substring(0, 1));
            maghrib_hourRight.setText(formattedDateTime.substring(1, 2));
            maghrib_minLeft.setText(formattedDateTime.substring(3, 4));
            maghrib_minRight.setText(formattedDateTime.substring(4, 5));
            
            ishadate = isha_cal.getTime();
            formattedDateTime = dateFormat.format(ishadate);
            
            isha_hourLeft.setText(formattedDateTime.substring(0, 1));
            isha_hourRight.setText(formattedDateTime.substring(1, 2));
            isha_minLeft.setText(formattedDateTime.substring(3, 4));
            isha_minRight.setText(formattedDateTime.substring(4, 5));
            
            time_Separator1.setText(":");
            time_Separator2.setText(":");
            time_Separator3.setText(":");
            time_Separator4.setText(":");
            time_Separator5.setText(":");
            time_Separator6.setText(":");
            time_Separator8.setText(":");
            time_Separator9.setText(":");
            
            
            fajrjamaatdate = fajr_jamaat_cal.getTime();
            formattedDateTime = dateFormat.format(fajrjamaatdate);
            
            fajr_jamma_hourLeft.setText(formattedDateTime.substring(0, 1));
            fajr_jamma_hourRight.setText(formattedDateTime.substring(1, 2));
            fajr_jamma_minLeft.setText(formattedDateTime.substring(3, 4));
            fajr_jamma_minRight.setText(formattedDateTime.substring(4, 5));
            

            
            zuhr_jamma_hourLeft.setText(zuhr_jamaat.substring(0, 1));
            zuhr_jamma_hourRight.setText(zuhr_jamaat.substring(1, 2));
            zuhr_jamma_minLeft.setText(zuhr_jamaat.substring(3, 4));
            zuhr_jamma_minRight.setText(zuhr_jamaat.substring(4, 5));
             
            
            asrjamaatdate = asr_jamaat_cal.getTime();
            formattedDateTime = dateFormat.format(asrjamaatdate);
            
            asr_jamma_hourLeft.setText(formattedDateTime.substring(0, 1));
            asr_jamma_hourRight.setText(formattedDateTime.substring(1, 2));
            asr_jamma_minLeft.setText(formattedDateTime.substring(3, 4));
            asr_jamma_minRight.setText(formattedDateTime.substring(4, 5));
            
//            maghribjamaatdate = maghrib_cal.getTime();
            maghribjamaatdate = maghrib_jamaat_cal.getTime();
            formattedDateTime = dateFormat.format(maghribjamaatdate);
            maghrib_jamma_hourLeft.setText(formattedDateTime.substring(0, 1));
            maghrib_jamma_hourRight.setText(formattedDateTime.substring(1, 2));
            maghrib_jamma_minLeft.setText(formattedDateTime.substring(3, 4));
            maghrib_jamma_minRight.setText(formattedDateTime.substring(4, 5));
            
            ishajamaatdate = isha_jamaat_cal.getTime();
            formattedDateTime = dateFormat.format(ishajamaatdate);
            
            isha_jamma_hourLeft.setText(formattedDateTime.substring(0, 1));
            isha_jamma_hourRight.setText(formattedDateTime.substring(1, 2));
            isha_jamma_minLeft.setText(formattedDateTime.substring(3, 4));
            isha_jamma_minRight.setText(formattedDateTime.substring(4, 5));
            
            friday_hourLeft.setText(friday_jamaat.substring(0, 1));
            friday_hourRight.setText(friday_jamaat.substring(1, 2));
            friday_minLeft.setText(friday_jamaat.substring(3, 4));
            friday_minRight.setText(friday_jamaat.substring(4, 5));
            friday2_hourLeft.setText(friday2_jamaat.substring(0, 1));
            friday2_hourRight.setText(friday2_jamaat.substring(1, 2));
            friday2_minLeft.setText(friday2_jamaat.substring(3, 4));
            friday2_minRight.setText(friday2_jamaat.substring(4, 5));

            
             
            time_jamma_Separator1.setText(":");
            time_jamma_Separator2.setText(":");
            time_jamma_Separator3.setText(":");
            time_jamma_Separator4.setText(":");
            time_jamma_Separator5.setText(":");   
        }
        
//==Update Moon Images============================================================  
        
        if (update_moon_image && moon_calcs_display)
        {   
            update_moon_image = false;
            
            if (moonPhase == 200 )
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/0%.png")));      
                
               if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
               
               else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }

                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }
            
            if (moonPhase <= 2 && moonPhase >= 0 )
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/0%.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }
        
            else if (moonPhase>2 && moonPhase<=10 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/3%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }

                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }
                
               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>10 && moonPhase<=17 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/12%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>17 && moonPhase<=32 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/21%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }
            else if (moonPhase>32 && moonPhase<=43 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/38%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>43 && moonPhase<=52 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/47%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>52 && moonPhase<=61 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/56%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>61 && moonPhase<=70 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/65%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>70 && moonPhase<=78 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/74%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>78 && moonPhase<=87 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/82%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>87 && moonPhase<=99 && isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/91%WA.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase== 100)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/100%.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>2 && moonPhase<=12 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/8%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>12 && moonPhase<=20 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/16%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>20 && moonPhase<=28 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/24%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>28 && moonPhase<=36 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/32%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }

                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }
               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>36 && moonPhase<=44 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/40%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>44 && moonPhase<=52 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/48%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>52 && moonPhase<=59 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/56%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>59 && moonPhase<=67 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/63%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>67 && moonPhase<=74 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/71%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>74 && moonPhase<=82 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/78%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>82 && moonPhase<=90 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/86%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

            else if (moonPhase>90 && moonPhase<=99 && !isWaning)
            {
                Moon_Image_Label.setGraphic(null);
                ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/94%WX.png")));      
                if (orientation.equals("horizontal") )
                {
                    Moon_img.setFitWidth(50);
                    Moon_img.setFitHeight(50);
                }
                
                else if (orientation.equals("camera_mode") )
                {
                    Moon_img.setFitWidth(camera_mode_moon_img_width);
                    Moon_img.setFitHeight(camera_mode_moon_img_height);
                }

               else
               {
                   Moon_img.setFitWidth(120);
                   Moon_img.setFitHeight(120);
               }
                Moon_img.setPreserveRatio(false);
                Moon_img.setSmooth(true);        
                Moon_Image_Label.setGraphic(Moon_img);
            }

        }

//             if (isInternetReachable()){ System.out.println("connected"); control.setIndicatorStyle(SimpleIndicator.IndicatorStyle.GREEN);} else {System.out.println("not connected");control.setIndicatorStyle(SimpleIndicator.IndicatorStyle.RED);}

        }    
    

    //checks for connection to the internet through dummy request
//        public static boolean isInternetReachable()
//        {
//            try {
//                //make a URL to a known source
//                URL url = new URL("http://www.google.com");
//
//                //open a connection to that source
//                HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
//
//                //trying to retrieve data from the source. If there
//                //is no connection, this line will fail
//                Object objData = urlConnect.getContent();
//
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//                return false;
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            }
//            return true;
//        }

    
    public GridPane prayertime_pane() {
        
   GridPane prayertime_pane = new GridPane();
     
   if (orientation.equals("vertical") )
    {
        prayertime_pane.setId("prayertime_pane");
        prayertime_pane.setCache(false);       
//        prayertime_pane.setGridLinesVisible(false);
        prayertime_pane.setPadding(new Insets(0, 0, 20, 20));
        prayertime_pane.setAlignment(Pos.BASELINE_CENTER);
//        prayertime_pane.setVgap(20);
        prayertime_pane.setHgap(80);
        
        prayertime_pane.setConstraints(jamaat_Label_eng, 0, 1);
        prayertime_pane.getChildren().add(jamaat_Label_eng);      
        prayertime_pane.setConstraints(jamaat_Label_ar, 0, 1);
        prayertime_pane.getChildren().add(jamaat_Label_ar);
        
        prayertime_pane.setConstraints(athan_Label_eng, 1, 1);
        prayertime_pane.getChildren().add(athan_Label_eng);      
        prayertime_pane.setConstraints(athan_Label_ar, 1, 1);
        prayertime_pane.getChildren().add(athan_Label_ar);
//=============================  
        HBox fajrBox = new HBox();
        fajrBox.setSpacing(0);
        fajrBox.setMaxSize(180,60);
        fajrBox.setMinSize(180,60);
        fajrBox.setPrefSize(180,60);
        
        fajr_hourLeft.setId("hourLeft");
        fajr_hourRight.setId("hourLeft");
        time_Separator1.setId("hourLeft");
        fajr_minLeft.setId("hourLeft");
        fajr_minRight.setId("hourLeft");
        
        fajrBox.getChildren().addAll(fajr_hourLeft, fajr_hourRight, time_Separator1, fajr_minLeft, fajr_minRight);
        prayertime_pane.setConstraints(fajrBox, 1, 2);
        prayertime_pane.getChildren().add(fajrBox);
        
        prayertime_pane.setConstraints(fajr_Label_eng, 2, 2);
        prayertime_pane.getChildren().add(fajr_Label_eng);      
        prayertime_pane.setConstraints(fajr_Label_ar, 2, 2);
        prayertime_pane.getChildren().add(fajr_Label_ar);
        
        fajr_hourLeft.setText("-");
        fajr_hourRight.setText("-");
        fajr_minLeft.setText("-");
        fajr_minRight.setText("-");
        time_Separator1.setText(":");
        

//============================= 
        HBox zuhrBox = new HBox();
        zuhrBox.setSpacing(0);
        zuhrBox.setMaxSize(180,60);
        zuhrBox.setMinSize(180,60);
        zuhrBox.setPrefSize(180,60);
        zuhr_hourLeft.setId("hourLeft");
        zuhr_hourRight.setId("hourLeft");
        time_Separator3.setId("hourLeft");
        zuhr_minLeft.setId("hourLeft");
        zuhr_minRight.setId("hourLeft");
        zuhrBox.getChildren().addAll(zuhr_hourLeft, zuhr_hourRight, time_Separator3, zuhr_minLeft, zuhr_minRight);
        prayertime_pane.setConstraints(zuhrBox, 1, 4);
        prayertime_pane.getChildren().add(zuhrBox);
        
        prayertime_pane.setConstraints(zuhr_Label_eng, 2, 4);
        prayertime_pane.getChildren().add(zuhr_Label_eng);      
        prayertime_pane.setConstraints(zuhr_Label_ar, 2, 4);
        prayertime_pane.getChildren().add(zuhr_Label_ar);
        
        zuhr_hourLeft.setText("-");
        zuhr_hourRight.setText("-");
        zuhr_minLeft.setText("-");
        zuhr_minRight.setText("-");
        time_Separator3.setText(":");

//============================= 
        HBox asrBox = new HBox();
        asrBox.setSpacing(0);
        asrBox.setMaxSize(180,60);
        asrBox.setMinSize(180,60);
        asrBox.setPrefSize(180,60);
        asr_hourLeft.setId("hourLeft");
        asr_hourRight.setId("hourLeft");
        time_Separator4.setId("hourLeft");
        asr_minLeft.setId("hourLeft");
        asr_minRight.setId("hourLeft");
        asrBox.getChildren().addAll(asr_hourLeft, asr_hourRight, time_Separator4, asr_minLeft, asr_minRight);
        prayertime_pane.setConstraints(asrBox, 1, 6);
        prayertime_pane.getChildren().add(asrBox);
        
        prayertime_pane.setConstraints(asr_Label_eng, 2, 6);
        prayertime_pane.getChildren().add(asr_Label_eng);      
        prayertime_pane.setConstraints(asr_Label_ar, 2, 6);
        prayertime_pane.getChildren().add(asr_Label_ar);
        
        asr_hourLeft.setText("-");
        asr_hourRight.setText("-");
        asr_minLeft.setText("-");
        asr_minRight.setText("-");
        time_Separator4.setText(":");
        
//============================= 
        
        HBox maghribBox = new HBox();
        maghribBox.setSpacing(0);
        maghribBox.setMaxSize(180,60);
        maghribBox.setMinSize(180,60);
        maghribBox.setPrefSize(180,60);
        maghrib_hourLeft.setId("hourLeft");
        maghrib_hourRight.setId("hourLeft");
        time_Separator5.setId("hourLeft");
        maghrib_minLeft.setId("hourLeft");
        maghrib_minRight.setId("hourLeft");
        maghribBox.getChildren().addAll(maghrib_hourLeft, maghrib_hourRight, time_Separator5, maghrib_minLeft, maghrib_minRight);
        prayertime_pane.setConstraints(maghribBox, 1, 8);
        prayertime_pane.getChildren().add(maghribBox);
        
        prayertime_pane.setConstraints(maghrib_Label_eng, 2, 8);
        prayertime_pane.getChildren().add(maghrib_Label_eng);      
        prayertime_pane.setConstraints(maghrib_Label_ar, 2, 8);
        prayertime_pane.getChildren().add(maghrib_Label_ar);
        
        maghrib_hourLeft.setText("-");
        maghrib_hourRight.setText("-");
        maghrib_minLeft.setText("-");
        maghrib_minRight.setText("-");
        time_Separator5.setText(":");

//============================= 
        
        HBox ishaBox = new HBox();
        ishaBox.setSpacing(0);
        ishaBox.setMaxSize(180,60);
        ishaBox.setMinSize(180,60);
        ishaBox.setPrefSize(180,60);
        isha_hourLeft.setId("hourLeft");
        isha_hourRight.setId("hourLeft");
        time_Separator6.setId("hourLeft");
        isha_minLeft.setId("hourLeft");
        isha_minRight.setId("hourLeft");
        ishaBox.getChildren().addAll(isha_hourLeft, isha_hourRight, time_Separator6, isha_minLeft, isha_minRight);
        prayertime_pane.setConstraints(ishaBox, 1, 10);
        prayertime_pane.getChildren().add(ishaBox);
        
        prayertime_pane.setConstraints(isha_Label_eng, 2, 10);
        prayertime_pane.getChildren().add(isha_Label_eng);      
        prayertime_pane.setConstraints(isha_Label_ar, 2, 10);
        prayertime_pane.getChildren().add(isha_Label_ar);
        
        isha_hourLeft.setText("-");
        isha_hourRight.setText("-");
        isha_minLeft.setText("-");
        isha_minRight.setText("-");
        time_Separator6.setText(":");

         
        HBox fridayBox = new HBox();
        fridayBox.setSpacing(0);
        fridayBox.setMaxSize(180,60);
        fridayBox.setMinSize(180,60);
        fridayBox.setPrefSize(180,60);
        friday_hourLeft.setId("hourLeft");
        friday_hourRight.setId("hourLeft");
        time_Separator8.setId("hourLeft");
        friday_minLeft.setId("hourLeft");
        friday_minRight.setId("hourLeft");
        fridayBox.getChildren().addAll(friday_hourLeft, friday_hourRight, time_Separator8, friday_minLeft, friday_minRight);
        prayertime_pane.setConstraints(fridayBox, 1, 12);
        prayertime_pane.getChildren().add(fridayBox);
//        
        
        prayertime_pane.setConstraints(friday_Label_eng, 2, 12);
        prayertime_pane.getChildren().add(friday_Label_eng);
        prayertime_pane.setConstraints(friday_Label_ar, 2, 12);
        prayertime_pane.getChildren().add(friday_Label_ar);
        
        friday_hourLeft.setText("-");
        friday_hourRight.setText("-");
        friday_minLeft.setText("-");
        friday_minRight.setText("-");
        time_Separator8.setText(":");
        
        
 //============================= 
        
        final Separator sepHor1 = new Separator();
        prayertime_pane.setValignment(sepHor1,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor1, 0, 3);
        prayertime_pane.setColumnSpan(sepHor1, 3);
        prayertime_pane.getChildren().add(sepHor1);   
        
        final Separator sepHor2 = new Separator();
        prayertime_pane.setValignment(sepHor2,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor2, 0, 5);
        prayertime_pane.setColumnSpan(sepHor2, 3);
        prayertime_pane.getChildren().add(sepHor2);
        
        final Separator sepHor3 = new Separator();
        prayertime_pane.setValignment(sepHor3,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor3, 0, 7);
        prayertime_pane.setColumnSpan(sepHor3, 3);
        prayertime_pane.getChildren().add(sepHor3);
        
        final Separator sepHor4 = new Separator();
        prayertime_pane.setValignment(sepHor4,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor4, 0, 9);
        prayertime_pane.setColumnSpan(sepHor4, 3);
        prayertime_pane.getChildren().add(sepHor4);
        
        final Separator sepHor5 = new Separator();
        prayertime_pane.setValignment(sepHor5,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor5, 0, 11);
        prayertime_pane.setColumnSpan(sepHor5, 3);
        prayertime_pane.getChildren().add(sepHor5);
        
//        final Separator sepHor6 = new Separator();
//        prayertime_pane.setValignment(sepHor6,VPos.CENTER);
//        prayertime_pane.setConstraints(sepHor6, 1, 14);
//        prayertime_pane.setColumnSpan(sepHor6, 2);
//        prayertime_pane.getChildren().add(sepHor6);

//========= Jamama=======
        
        
//=============================  
        HBox fajr_jamma_Box = new HBox();
        fajr_jamma_Box.setSpacing(0);
        fajr_jamma_Box.setMaxSize(180,60);
        fajr_jamma_hourLeft.setId("hourLeft");
        fajr_jamma_hourRight.setId("hourLeft");
        time_jamma_Separator1.setId("hourLeft");
        fajr_jamma_minLeft.setId("hourLeft");
        fajr_jamma_minRight.setId("hourLeft");
        fajr_jamma_Box.getChildren().addAll(fajr_jamma_hourLeft, fajr_jamma_hourRight, time_jamma_Separator1, fajr_jamma_minLeft, fajr_jamma_minRight);
        prayertime_pane.setConstraints(fajr_jamma_Box, 0, 2);
        prayertime_pane.getChildren().add(fajr_jamma_Box);
        
        fajr_jamma_hourLeft.setText("-");
        fajr_jamma_hourRight.setText("-");
        fajr_jamma_minLeft.setText("-");
        fajr_jamma_minRight.setText("-");
        time_jamma_Separator1.setText(":");
        
//============================= 
        HBox zuhr_jamma_Box = new HBox();
        zuhr_jamma_Box.setSpacing(0);
        zuhr_jamma_Box.setMaxSize(180,60);
        zuhr_jamma_hourLeft.setId("hourLeft");
        zuhr_jamma_hourRight.setId("hourLeft");
        time_jamma_Separator2.setId("hourLeft");
        zuhr_jamma_minLeft.setId("hourLeft");
        zuhr_jamma_minRight.setId("hourLeft");
        zuhr_jamma_Box.getChildren().addAll(zuhr_jamma_hourLeft, zuhr_jamma_hourRight, time_jamma_Separator2, zuhr_jamma_minLeft, zuhr_jamma_minRight);
        prayertime_pane.setConstraints(zuhr_jamma_Box, 0, 4);
        prayertime_pane.getChildren().add(zuhr_jamma_Box);
        
        zuhr_jamma_hourLeft.setText("-");
        zuhr_jamma_hourRight.setText("-");
        zuhr_jamma_minLeft.setText("-");
        zuhr_jamma_minRight.setText("-");
        time_jamma_Separator2.setText(":");

//============================= 
        HBox asr_jamma_Box = new HBox();
        asr_jamma_Box.setSpacing(0);
        asr_jamma_Box.setMaxSize(180,60);
        asr_jamma_minRight.setId("hourLeft");
        asr_jamma_minLeft.setId("hourLeft");
        time_jamma_Separator3.setId("hourLeft");
        asr_jamma_hourRight.setId("hourLeft");
        asr_jamma_hourLeft.setId("hourLeft");
        asr_jamma_Box.getChildren().addAll(asr_jamma_hourLeft, asr_jamma_hourRight, time_jamma_Separator3, asr_jamma_minLeft, asr_jamma_minRight);
        prayertime_pane.setConstraints(asr_jamma_Box, 0, 6);
        prayertime_pane.getChildren().add(asr_jamma_Box);
        
        asr_jamma_hourLeft.setText("-");
        asr_jamma_hourRight.setText("-");
        asr_jamma_minLeft.setText("-");
        asr_jamma_minRight.setText("-");
        time_jamma_Separator3.setText(":");
        
//============================= 
        
        HBox maghrib_jamma_Box = new HBox();
        maghrib_jamma_Box.setSpacing(0);
        maghrib_jamma_Box.setMaxSize(180,60);
        maghrib_jamma_minRight.setId("hourLeft");
        maghrib_jamma_minLeft.setId("hourLeft");
        time_jamma_Separator4.setId("hourLeft");
        maghrib_jamma_hourRight.setId("hourLeft");
        maghrib_jamma_hourLeft.setId("hourLeft");
        maghrib_jamma_Box.getChildren().addAll(maghrib_jamma_hourLeft, maghrib_jamma_hourRight, time_jamma_Separator4, maghrib_jamma_minLeft, maghrib_jamma_minRight);
        prayertime_pane.setConstraints(maghrib_jamma_Box, 0, 8);
        prayertime_pane.getChildren().add(maghrib_jamma_Box);
        
        maghrib_jamma_hourLeft.setText("-");
        maghrib_jamma_hourRight.setText("-");
        maghrib_jamma_minLeft.setText("-");
        maghrib_jamma_minRight.setText("-");
        time_jamma_Separator4.setText(":");
//============================= 
        
        HBox isha_jamma_Box = new HBox();
        isha_jamma_Box.setSpacing(0);
        isha_jamma_Box.setMaxSize(180,60);
        isha_jamma_hourLeft.setId("hourLeft");
        isha_jamma_hourRight.setId("hourLeft");
        time_jamma_Separator5.setId("hourLeft");
        isha_jamma_minLeft.setId("hourLeft");
        isha_jamma_minRight.setId("hourLeft");
        isha_jamma_Box.getChildren().addAll(isha_jamma_hourLeft, isha_jamma_hourRight, time_jamma_Separator5, isha_jamma_minLeft, isha_jamma_minRight);
        prayertime_pane.setConstraints(isha_jamma_Box, 0, 10);
        prayertime_pane.getChildren().add(isha_jamma_Box);
        
        isha_jamma_hourLeft.setText("-");
        isha_jamma_hourRight.setText("-");
        isha_jamma_minLeft.setText("-");
        isha_jamma_minRight.setText("-");
        time_jamma_Separator5.setText(":");

        HBox fridayBox2 = new HBox();
        fridayBox2.setSpacing(0);
        fridayBox2.setMaxSize(180,60);
        fridayBox2.setMinSize(180,60);
        fridayBox2.setPrefSize(180,60);
        friday2_hourLeft.setId("hourLeft");
        friday2_hourRight.setId("hourLeft");
        time_Separator9.setId("hourLeft");
        friday2_minLeft.setId("hourLeft");
        friday2_minRight.setId("hourLeft");
        fridayBox2.getChildren().addAll(friday2_hourLeft, friday2_hourRight, time_Separator9, friday2_minLeft, friday2_minRight);
        prayertime_pane.setConstraints(fridayBox2, 0, 12);
        prayertime_pane.getChildren().add(fridayBox2);
        
        friday2_hourLeft.setText("-");
        friday2_hourRight.setText("-");
        friday2_minLeft.setText("-");
        friday2_minRight.setText("-");
        time_Separator9.setText(":");

    }
   
   else if (orientation.equals("horizontal") )
    {
        
        
            prayertime_pane.getRowConstraints().setAll(
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(1).build()
            );
        
     
       if (show_friday)
        
        {
            prayertime_pane.getRowConstraints().setAll(
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(0).build(),
                RowConstraintsBuilder.create().minHeight(50).build()
            );
        }
     
//       prayertime_pane.getColumnConstraints().setAll(
//                ColumnConstraintsBuilder.create().build(),
//                ColumnConstraintsBuilder.create().build(),
//                ColumnConstraintsBuilder.create().minWidth(50).build(),
//                ColumnConstraintsBuilder.create().build()    
//   
//        );
        if (!show_friday){prayertime_pane.setId("prayertime_pane");}
        else {prayertime_pane.setId("prayertime_pane_friday");}
        
        prayertime_pane.setCache(false);       
//        prayertime_pane.setGridLinesVisible(true);
//        prayertime_pane.setPadding(new Insets(0, 0, 3, 0));
        prayertime_pane.setAlignment(Pos.BASELINE_CENTER);
//        prayertime_pane.setVgap(5);
        prayertime_pane.setHgap(13);
        
        prayertime_pane.setConstraints(jamaat_Label_eng, 0, 1);
//        jamaat_Label_eng.setMaxHeight(1);
        prayertime_pane.getChildren().add(jamaat_Label_eng);      
        prayertime_pane.setConstraints(jamaat_Label_ar, 0, 1);
        prayertime_pane.getChildren().add(jamaat_Label_ar);
        
        prayertime_pane.setConstraints(athan_Label_eng, 1, 1);
//        athan_Label_eng.setMaxHeight(1);
        prayertime_pane.getChildren().add(athan_Label_eng); 
        
        prayertime_pane.setConstraints(athan_Label_ar, 1, 1);
        prayertime_pane.getChildren().add(athan_Label_ar);
//=============================  
        HBox fajrBox = new HBox();
//        fajrBox.setSpacing(0);
        fajrBox.setMaxSize(80,1);
//        fajrBox.setMinSize(100,40);
//        fajrBox.setPrefSize(100,15);
        

        if (show_friday)
        
        {
            fajr_hourLeft.setId("hourLeft_friday_row");
            fajr_hourRight.setId("hourLeft_friday_row");
            time_Separator1.setId("hourLeft_friday_row");
            fajr_minLeft.setId("hourLeft_friday_row");
            fajr_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            fajr_hourLeft.setId("hourLeft");
            fajr_hourRight.setId("hourLeft");
            time_Separator1.setId("hourLeft");
            fajr_minLeft.setId("hourLeft");
            fajr_minRight.setId("hourLeft");
        }
        
        
        
        fajrBox.getChildren().addAll(fajr_hourLeft, fajr_hourRight, time_Separator1, fajr_minLeft, fajr_minRight);
        prayertime_pane.setConstraints(fajrBox, 1, 2);
        prayertime_pane.getChildren().add(fajrBox);
        
//        fajr_Label_eng.setMaxSize(100,40);
//        fajr_Label_eng.setMinSize(70,17);
//        fajr_Label_eng.setPrefSize(100,40);
        
//        fajr_Label_ar.setAlignment(Pos.TOP_LEFT);
//        fajr_Label_eng.setAlignment(Pos.TOP_LEFT);
        fajr_Label_ar.setTranslateY(-10);
//        fajr_Label_ar.setMaxSize(100,40);
//        fajr_Label_ar.setMinSize(70,17);
//        fajr_Label_ar.setPrefSize(100,40);
                
                
        prayertime_pane.setConstraints(fajr_Label_eng, 3, 2);
        prayertime_pane.getChildren().add(fajr_Label_eng);      
        prayertime_pane.setConstraints(fajr_Label_ar, 3, 2);
        prayertime_pane.getChildren().add(fajr_Label_ar);
        
        fajr_hourLeft.setText("-");
        fajr_hourRight.setText("-");
        fajr_minLeft.setText("-");
        fajr_minRight.setText("-");
        time_Separator1.setText(":");
        

//============================= 
        HBox zuhrBox = new HBox();
//        zuhrBox.setSpacing(0);
        zuhrBox.setMaxSize(80,1);
//        zuhrBox.setMinSize(100,15);
//        zuhrBox.setPrefSize(100,15);
        
        if (show_friday)
        
        {
            zuhr_hourLeft.setId("hourLeft_friday_row");
            zuhr_hourRight.setId("hourLeft_friday_row");
            time_Separator3.setId("hourLeft_friday_row");
            zuhr_minLeft.setId("hourLeft_friday_row");
            zuhr_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            zuhr_hourLeft.setId("hourLeft");
            zuhr_hourRight.setId("hourLeft");
            time_Separator3.setId("hourLeft");
            zuhr_minLeft.setId("hourLeft");
            zuhr_minRight.setId("hourLeft");
        }
        zuhrBox.getChildren().addAll(zuhr_hourLeft, zuhr_hourRight, time_Separator3, zuhr_minLeft, zuhr_minRight);
        prayertime_pane.setConstraints(zuhrBox, 1, 4);
        prayertime_pane.getChildren().add(zuhrBox);
        
//        zuhr_Label_eng.setMaxSize(100,45);
//        zuhr_Label_eng.setMinSize(100,45);
//        zuhr_Label_eng.setPrefSize(100,45);
//        zuhr_Label_ar.setMaxSize(100,45);
//        zuhr_Label_ar.setMinSize(100,45);
//        zuhr_Label_ar.setPrefSize(100,45);
        
        prayertime_pane.setConstraints(zuhr_Label_eng, 3, 4);
        prayertime_pane.getChildren().add(zuhr_Label_eng);      
        prayertime_pane.setConstraints(zuhr_Label_ar, 3, 4);
        prayertime_pane.getChildren().add(zuhr_Label_ar);
        
        zuhr_hourLeft.setText("-");
        zuhr_hourRight.setText("-");
        zuhr_minLeft.setText("-");
        zuhr_minRight.setText("-");
        time_Separator3.setText(":");

//============================= 
        HBox asrBox = new HBox();
//        asrBox.setSpacing(0);
        asrBox.setMaxSize(80,1);
//        asrBox.setMinSize(100,15);
//        asrBox.setPrefSize(100,15);

        
        if (show_friday)
        
        {
            asr_hourLeft.setId("hourLeft_friday_row");
            asr_hourRight.setId("hourLeft_friday_row");
            time_Separator4.setId("hourLeft_friday_row");
            asr_minLeft.setId("hourLeft_friday_row");
            asr_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            asr_hourLeft.setId("hourLeft");
            asr_hourRight.setId("hourLeft");
            time_Separator4.setId("hourLeft");
            asr_minLeft.setId("hourLeft");
            asr_minRight.setId("hourLeft");
        }
        
        asrBox.getChildren().addAll(asr_hourLeft, asr_hourRight, time_Separator4, asr_minLeft, asr_minRight);
        prayertime_pane.setConstraints(asrBox, 1, 6);
        prayertime_pane.getChildren().add(asrBox);
        
//        asr_Label_eng.setMaxSize(100,40);
//        asr_Label_eng.setMinSize(100,40);
//        asr_Label_eng.setPrefSize(100,40);
//        asr_Label_ar.setMaxSize(100,40);
//        asr_Label_ar.setMinSize(100,40);
//        asr_Label_ar.setPrefSize(100,40);
        
        prayertime_pane.setConstraints(asr_Label_eng, 3, 6);
        prayertime_pane.getChildren().add(asr_Label_eng);      
        prayertime_pane.setConstraints(asr_Label_ar, 3, 6);
        prayertime_pane.getChildren().add(asr_Label_ar);
        
        asr_hourLeft.setText("-");
        asr_hourRight.setText("-");
        asr_minLeft.setText("-");
        asr_minRight.setText("-");
        time_Separator4.setText(":");
        
//============================= 
        
        HBox maghribBox = new HBox();
//        maghribBox.setSpacing(0);
        maghribBox.setMaxSize(80,1);
//        maghribBox.setMinSize(100,15);
//        maghribBox.setPrefSize(100,15);

        
        
        if (show_friday)
        
        {
            maghrib_hourLeft.setId("hourLeft_friday_row");
            maghrib_hourRight.setId("hourLeft_friday_row");
            time_Separator5.setId("hourLeft_friday_row");
            maghrib_minLeft.setId("hourLeft_friday_row");
            maghrib_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            maghrib_hourLeft.setId("hourLeft");
            maghrib_hourRight.setId("hourLeft");
            time_Separator5.setId("hourLeft");
            maghrib_minLeft.setId("hourLeft");
            maghrib_minRight.setId("hourLeft");
        }
        
        
        maghribBox.getChildren().addAll(maghrib_hourLeft, maghrib_hourRight, time_Separator5, maghrib_minLeft, maghrib_minRight);
        prayertime_pane.setConstraints(maghribBox, 1, 8);
        prayertime_pane.getChildren().add(maghribBox);
        
//        maghrib_Label_eng.setMaxSize(100,40);
//        maghrib_Label_eng.setMinSize(100,40);
//        maghrib_Label_eng.setPrefSize(100,40);
//        maghrib_Label_ar.setMaxSize(100,40);
//        maghrib_Label_ar.setMinSize(100,40);
//        maghrib_Label_ar.setPrefSize(100,40);
        
        prayertime_pane.setConstraints(maghrib_Label_eng, 3, 8);
        prayertime_pane.getChildren().add(maghrib_Label_eng);      
        prayertime_pane.setConstraints(maghrib_Label_ar, 3, 8);
        prayertime_pane.getChildren().add(maghrib_Label_ar);
        
        maghrib_hourLeft.setText("-");
        maghrib_hourRight.setText("-");
        maghrib_minLeft.setText("-");
        maghrib_minRight.setText("-");
        time_Separator5.setText(":");

//============================= 
        
        HBox ishaBox = new HBox();
//        ishaBox.setSpacing(0);
        ishaBox.setMaxSize(80,1);
//        ishaBox.setMinSize(100,15);
//        ishaBox.setPrefSize(100,15);
        isha_hourLeft.setId("hourLeft");
        isha_hourRight.setId("hourLeft");
        time_Separator6.setId("hourLeft");
        isha_minLeft.setId("hourLeft");
        isha_minRight.setId("hourLeft");
        
        if (show_friday)
        
        {
            isha_hourLeft.setId("hourLeft_friday_row");
            isha_hourRight.setId("hourLeft_friday_row");
            time_Separator6.setId("hourLeft_friday_row");
            isha_minLeft.setId("hourLeft_friday_row");
            isha_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            isha_hourLeft.setId("hourLeft");
            isha_hourRight.setId("hourLeft");
            time_Separator6.setId("hourLeft");
            isha_minLeft.setId("hourLeft");
            isha_minRight.setId("hourLeft");
        }
        
        
        ishaBox.getChildren().addAll(isha_hourLeft, isha_hourRight, time_Separator6, isha_minLeft, isha_minRight);
        prayertime_pane.setConstraints(ishaBox, 1, 10);
        prayertime_pane.getChildren().add(ishaBox);
        
//        isha_Label_eng.setMaxSize(100,40);
//        isha_Label_eng.setMinSize(100,40);
//        isha_Label_eng.setPrefSize(100,40);
//        isha_Label_ar.setMaxSize(100,40);
//        isha_Label_ar.setMinSize(100,40);
//        isha_Label_ar.setPrefSize(100,40);
        
        prayertime_pane.setConstraints(isha_Label_eng, 3, 10);
        prayertime_pane.getChildren().add(isha_Label_eng);      
        prayertime_pane.setConstraints(isha_Label_ar, 3, 10);
        prayertime_pane.getChildren().add(isha_Label_ar);
        
        isha_hourLeft.setText("-");
        isha_hourRight.setText("-");
        isha_minLeft.setText("-");
        isha_minRight.setText("-");
        time_Separator6.setText(":");
        
        
        //=============================  
         
        if (show_friday)
        
        {
            HBox fridayBox = new HBox();
    //        fridayBox.setSpacing(0);
            fridayBox.setMaxSize(80,1);
    //        fridayBox.setMinSize(100,15);
    //        fridayBox.setPrefSize(100,15);
            
            
            friday_hourLeft.setId("hourLeft_friday_row");
            friday_hourRight.setId("hourLeft_friday_row");
            time_Separator8.setId("hourLeft_friday_row");
            friday_minLeft.setId("hourLeft_friday_row");
            friday_minRight.setId("hourLeft_friday_row");
            
            fridayBox.getChildren().addAll(friday_hourLeft, friday_hourRight, time_Separator8, friday_minLeft, friday_minRight);
            prayertime_pane.setConstraints(fridayBox, 0, 13);
            prayertime_pane.getChildren().add(fridayBox);

    //        friday_Label_eng.setMaxSize(100,40);
    //        friday_Label_eng.setMinSize(100,40);
    //        friday_Label_eng.setPrefSize(100,40);
    //        friday_Label_ar.setMaxSize(100,40);
    //        friday_Label_ar.setMinSize(100,40);
    //        friday_Label_ar.setPrefSize(100,40);

            prayertime_pane.setConstraints(friday_Label_eng, 3, 13);
            prayertime_pane.getChildren().add(friday_Label_eng);
            prayertime_pane.setConstraints(friday_Label_ar, 3, 13);
            prayertime_pane.getChildren().add(friday_Label_ar);

            friday_hourLeft.setText("-");
            friday_hourRight.setText("-");
            friday_minLeft.setText("-");
            friday_minRight.setText("-");
            time_Separator8.setText(":");

            final Separator sepHor5 = new Separator();
            prayertime_pane.setValignment(sepHor5,VPos.CENTER);
            prayertime_pane.setConstraints(sepHor5, 0, 12);
            prayertime_pane.setColumnSpan(sepHor5, 3);
//            sepHor5.setMinHeight(1);
            prayertime_pane.getChildren().add(sepHor5);
        
        }
 //============================= 
        
        final Separator sepHor1 = new Separator();
        prayertime_pane.setValignment(sepHor1,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor1, 0, 3);
        prayertime_pane.setColumnSpan(sepHor1, 3);
//        sepHor1.setMaxSize(100,15);
//        sepHor1.setMinSize(100,15);
//        sepHor1.setMinHeight(1);
        prayertime_pane.getChildren().add(sepHor1);   
        
        final Separator sepHor2 = new Separator();
        prayertime_pane.setValignment(sepHor2,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor2, 0, 5);
        prayertime_pane.setColumnSpan(sepHor2, 3);
//        sepHor2.setMinHeight(1);
        prayertime_pane.getChildren().add(sepHor2);
        
        final Separator sepHor3 = new Separator();
        prayertime_pane.setValignment(sepHor3,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor3, 0, 7);
        prayertime_pane.setColumnSpan(sepHor3, 3);
//        sepHor3.setMinHeight(1);
        prayertime_pane.getChildren().add(sepHor3);
        
        final Separator sepHor4 = new Separator();
        prayertime_pane.setValignment(sepHor4,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor4, 0, 9);
        prayertime_pane.setColumnSpan(sepHor4, 3);
//        sepHor4.setMinHeight(1);
        prayertime_pane.getChildren().add(sepHor4);
        
        
        
        

//========= Jamama=======
               
//=============================  
        HBox fajr_jamma_Box = new HBox();
//        fajr_jamma_Box.setSpacing(0);
        fajr_jamma_Box.setMaxSize(80,1);
//        fajr_jamma_Box.setMinSize(100,40);
//        fajr_jamma_Box.setPrefSize(100,15);

        
        if (show_friday)
        
        {
            fajr_jamma_hourLeft.setId("hourLeft_friday_row");
            fajr_jamma_hourRight.setId("hourLeft_friday_row");
            time_jamma_Separator1.setId("hourLeft_friday_row");
            fajr_jamma_minLeft.setId("hourLeft_friday_row");
            fajr_jamma_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            fajr_jamma_hourLeft.setId("hourLeft");
            fajr_jamma_hourRight.setId("hourLeft");
            time_jamma_Separator1.setId("hourLeft");
            fajr_jamma_minLeft.setId("hourLeft");
            fajr_jamma_minRight.setId("hourLeft");
        }
        
        
        fajr_jamma_Box.getChildren().addAll(fajr_jamma_hourLeft, fajr_jamma_hourRight, time_jamma_Separator1, fajr_jamma_minLeft, fajr_jamma_minRight);
        prayertime_pane.setConstraints(fajr_jamma_Box, 0, 2);
        prayertime_pane.getChildren().add(fajr_jamma_Box);
        
        fajr_jamma_hourLeft.setText("-");
        fajr_jamma_hourRight.setText("-");
        fajr_jamma_minLeft.setText("-");
        fajr_jamma_minRight.setText("-");
        time_jamma_Separator1.setText(":");
        
//============================= 
        HBox zuhr_jamma_Box = new HBox();
//        zuhr_jamma_Box.setSpacing(0);
        zuhr_jamma_Box.setMaxSize(80,1);
//        zuhr_jamma_Box.setMinSize(100,15);
//        zuhr_jamma_Box.setPrefSize(100,15);

        
        if (show_friday)
        
        {
            zuhr_jamma_hourLeft.setId("hourLeft_friday_row");
            zuhr_jamma_hourRight.setId("hourLeft_friday_row");
            time_jamma_Separator2.setId("hourLeft_friday_row");
            zuhr_jamma_minLeft.setId("hourLeft_friday_row");
            zuhr_jamma_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            zuhr_jamma_hourLeft.setId("hourLeft");
            zuhr_jamma_hourRight.setId("hourLeft");
            time_jamma_Separator2.setId("hourLeft");
            zuhr_jamma_minLeft.setId("hourLeft");
            zuhr_jamma_minRight.setId("hourLeft");
        }
        
        
        zuhr_jamma_Box.getChildren().addAll(zuhr_jamma_hourLeft, zuhr_jamma_hourRight, time_jamma_Separator2, zuhr_jamma_minLeft, zuhr_jamma_minRight);
        prayertime_pane.setConstraints(zuhr_jamma_Box, 0, 4);
        prayertime_pane.getChildren().add(zuhr_jamma_Box);
        
        zuhr_jamma_hourLeft.setText("-");
        zuhr_jamma_hourRight.setText("-");
        zuhr_jamma_minLeft.setText("-");
        zuhr_jamma_minRight.setText("-");
        time_jamma_Separator2.setText(":");

//============================= 
        HBox asr_jamma_Box = new HBox();
//        asr_jamma_Box.setSpacing(0);
        asr_jamma_Box.setMaxSize(80,1);
//        asr_jamma_Box.setMinSize(100,15);
//        asr_jamma_Box.setPrefSize(100,15);
 
        
        if (show_friday)
        
        {
            asr_jamma_minRight.setId("hourLeft_friday_row");
            asr_jamma_minLeft.setId("hourLeft_friday_row");
            time_jamma_Separator3.setId("hourLeft_friday_row");
            asr_jamma_hourRight.setId("hourLeft_friday_row");
            asr_jamma_hourLeft.setId("hourLeft_friday_row");   
        }
        
        else
        {
            asr_jamma_minRight.setId("hourLeft");
            asr_jamma_minLeft.setId("hourLeft");
            time_jamma_Separator3.setId("hourLeft");
            asr_jamma_hourRight.setId("hourLeft");
            asr_jamma_hourLeft.setId("hourLeft");
        }
        
        
        asr_jamma_Box.getChildren().addAll(asr_jamma_hourLeft, asr_jamma_hourRight, time_jamma_Separator3, asr_jamma_minLeft, asr_jamma_minRight);
        prayertime_pane.setConstraints(asr_jamma_Box, 0, 6);
        prayertime_pane.getChildren().add(asr_jamma_Box);
        
        asr_jamma_hourLeft.setText("-");
        asr_jamma_hourRight.setText("-");
        asr_jamma_minLeft.setText("-");
        asr_jamma_minRight.setText("-");
        time_jamma_Separator3.setText(":");
        
//============================= 
        
        HBox maghrib_jamma_Box = new HBox();
//        maghrib_jamma_Box.setSpacing(0);
        maghrib_jamma_Box.setMaxSize(80,1);
//        maghrib_jamma_Box.setMinSize(100,15);
//        maghrib_jamma_Box.setPrefSize(100,15);

        if (show_friday)
        
        {
            maghrib_jamma_minRight.setId("hourLeft_friday_row");
            maghrib_jamma_minLeft.setId("hourLeft_friday_row");
            time_jamma_Separator4.setId("hourLeft_friday_row");
            maghrib_jamma_hourRight.setId("hourLeft_friday_row");
            maghrib_jamma_hourLeft.setId("hourLeft_friday_row");   
        }
        
        else
        {
            maghrib_jamma_minRight.setId("hourLeft");
            maghrib_jamma_minLeft.setId("hourLeft");
            time_jamma_Separator4.setId("hourLeft");
            maghrib_jamma_hourRight.setId("hourLeft");
            maghrib_jamma_hourLeft.setId("hourLeft");
        }
        
        
        maghrib_jamma_Box.getChildren().addAll(maghrib_jamma_hourLeft, maghrib_jamma_hourRight, time_jamma_Separator4, maghrib_jamma_minLeft, maghrib_jamma_minRight);
        prayertime_pane.setConstraints(maghrib_jamma_Box, 0, 8);
        prayertime_pane.getChildren().add(maghrib_jamma_Box);
        
        maghrib_jamma_hourLeft.setText("-");
        maghrib_jamma_hourRight.setText("-");
        maghrib_jamma_minLeft.setText("-");
        maghrib_jamma_minRight.setText("-");
        time_jamma_Separator4.setText(":");
//============================= 
        
        HBox isha_jamma_Box = new HBox();
//        isha_jamma_Box.setSpacing(0);
        isha_jamma_Box.setMaxSize(80,1);
//        isha_jamma_Box.setMinSize(100,15);
//        isha_jamma_Box.setPrefSize(100,15);

        
        
        if (show_friday)
        
        {
            isha_jamma_hourLeft.setId("hourLeft_friday_row");
            isha_jamma_hourRight.setId("hourLeft_friday_row");
            time_jamma_Separator5.setId("hourLeft_friday_row");
            isha_jamma_minLeft.setId("hourLeft_friday_row");
            isha_jamma_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            isha_jamma_hourLeft.setId("hourLeft");
            isha_jamma_hourRight.setId("hourLeft");
            time_jamma_Separator5.setId("hourLeft");
            isha_jamma_minLeft.setId("hourLeft");
            isha_jamma_minRight.setId("hourLeft");
        }
        
        
        isha_jamma_Box.getChildren().addAll(isha_jamma_hourLeft, isha_jamma_hourRight, time_jamma_Separator5, isha_jamma_minLeft, isha_jamma_minRight);
        prayertime_pane.setConstraints(isha_jamma_Box, 0, 10);
        prayertime_pane.getChildren().add(isha_jamma_Box);
        
        isha_jamma_hourLeft.setText("-");
        isha_jamma_hourRight.setText("-");
        isha_jamma_minLeft.setText("-");
        isha_jamma_minRight.setText("-");
        time_jamma_Separator5.setText(":");
        
        if(show_friday && double_friday)
            
        {
            HBox fridayBox2 = new HBox();
            fridayBox2.setMaxSize(80,1);
            friday2_hourLeft.setId("hourLeft_friday_row");
            friday2_hourRight.setId("hourLeft_friday_row");
            time_Separator9.setId("hourLeft_friday_row");
            friday2_minLeft.setId("hourLeft_friday_row");
            friday2_minRight.setId("hourLeft_friday_row");
            fridayBox2.getChildren().addAll(friday2_hourLeft, friday2_hourRight, time_Separator9, friday2_minLeft, friday2_minRight);
            prayertime_pane.setConstraints(fridayBox2, 1, 13);
            prayertime_pane.getChildren().add(fridayBox2);

            friday2_hourLeft.setText("-");
            friday2_hourRight.setText("-");
            friday2_minLeft.setText("-");
            friday2_minRight.setText("-");
            time_Separator9.setText(":");
        
        
        }


    }
   
   else
   {
        if (show_friday)
        
        {
            prayertime_pane.getRowConstraints().setAll(
                RowConstraintsBuilder.create().minHeight(1).build(),
                RowConstraintsBuilder.create().minHeight(105).build(),
                RowConstraintsBuilder.create().minHeight(108).build(),
                RowConstraintsBuilder.create().minHeight(1).build(),
                RowConstraintsBuilder.create().minHeight(108).build(),
                RowConstraintsBuilder.create().minHeight(1).build(),
                RowConstraintsBuilder.create().minHeight(108).build(),
                RowConstraintsBuilder.create().minHeight(1).build(),
                RowConstraintsBuilder.create().minHeight(108).build(),
                RowConstraintsBuilder.create().minHeight(1).build(),
                RowConstraintsBuilder.create().minHeight(108).build(),
                RowConstraintsBuilder.create().minHeight(1).build(),
                RowConstraintsBuilder.create().minHeight(1).build(),
                RowConstraintsBuilder.create().minHeight(108).build(),
                RowConstraintsBuilder.create().minHeight(10).build()
            );
        }
     
       prayertime_pane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().build(),
                ColumnConstraintsBuilder.create().build(),
                ColumnConstraintsBuilder.create().minWidth(50).build(),
                ColumnConstraintsBuilder.create().build()    
   
        );
        if (!show_friday){prayertime_pane.setId("prayertime_pane");}
        else {prayertime_pane.setId("prayertime_pane_friday");}
        
        prayertime_pane.setCache(false);       
//        prayertime_pane.setGridLinesVisible(true);
//        prayertime_pane.setPadding(new Insets(0, 0, 3, 0));
        prayertime_pane.setAlignment(Pos.BASELINE_CENTER);
//        prayertime_pane.setVgap(5);
        prayertime_pane.setHgap(13);
        
        prayertime_pane.setConstraints(jamaat_Label_eng, 0, 1);
//        jamaat_Label_eng.setMaxHeight(1);
        prayertime_pane.getChildren().add(jamaat_Label_eng);      
        prayertime_pane.setConstraints(jamaat_Label_ar, 0, 1);
        prayertime_pane.getChildren().add(jamaat_Label_ar);
        
        prayertime_pane.setConstraints(athan_Label_eng, 1, 1);
//        athan_Label_eng.setMaxHeight(1);
        prayertime_pane.getChildren().add(athan_Label_eng); 
        
        prayertime_pane.setConstraints(athan_Label_ar, 1, 1);
        prayertime_pane.getChildren().add(athan_Label_ar);
//=============================  
        HBox fajrBox = new HBox();
//        fajrBox.setSpacing(0);
        fajrBox.setMaxSize(80,1);
//        fajrBox.setMinSize(100,40);
//        fajrBox.setPrefSize(100,15);
        

        if (show_friday)
        
        {
            fajr_hourLeft.setId("hourLeft_friday_row");
            fajr_hourRight.setId("hourLeft_friday_row");
            time_Separator1.setId("hourLeft_friday_row");
            fajr_minLeft.setId("hourLeft_friday_row");
            fajr_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            fajr_hourLeft.setId("hourLeft");
            fajr_hourRight.setId("hourLeft");
            time_Separator1.setId("hourLeft");
            fajr_minLeft.setId("hourLeft");
            fajr_minRight.setId("hourLeft");
        }
        
        
        
        fajrBox.getChildren().addAll(fajr_hourLeft, fajr_hourRight, time_Separator1, fajr_minLeft, fajr_minRight);
        prayertime_pane.setConstraints(fajrBox, 1, 2);
        prayertime_pane.getChildren().add(fajrBox);
        
//        fajr_Label_eng.setMaxSize(100,40);
//        fajr_Label_eng.setMinSize(70,17);
//        fajr_Label_eng.setPrefSize(100,40);
        
//        fajr_Label_ar.setAlignment(Pos.TOP_LEFT);
//        fajr_Label_eng.setAlignment(Pos.TOP_LEFT);
        fajr_Label_ar.setTranslateY(-10);
//        fajr_Label_ar.setMaxSize(100,40);
//        fajr_Label_ar.setMinSize(70,17);
//        fajr_Label_ar.setPrefSize(100,40);
                
                
        prayertime_pane.setConstraints(fajr_Label_eng, 3, 2);
        prayertime_pane.getChildren().add(fajr_Label_eng);      
        prayertime_pane.setConstraints(fajr_Label_ar, 3, 2);
        prayertime_pane.getChildren().add(fajr_Label_ar);
        
        fajr_hourLeft.setText("-");
        fajr_hourRight.setText("-");
        fajr_minLeft.setText("-");
        fajr_minRight.setText("-");
        time_Separator1.setText(":");
        

//============================= 
        HBox zuhrBox = new HBox();
//        zuhrBox.setSpacing(0);
        zuhrBox.setMaxSize(80,1);
//        zuhrBox.setMinSize(100,15);
//        zuhrBox.setPrefSize(100,15);
        
        if (show_friday)
        
        {
            zuhr_hourLeft.setId("hourLeft_friday_row");
            zuhr_hourRight.setId("hourLeft_friday_row");
            time_Separator3.setId("hourLeft_friday_row");
            zuhr_minLeft.setId("hourLeft_friday_row");
            zuhr_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            zuhr_hourLeft.setId("hourLeft");
            zuhr_hourRight.setId("hourLeft");
            time_Separator3.setId("hourLeft");
            zuhr_minLeft.setId("hourLeft");
            zuhr_minRight.setId("hourLeft");
        }
        zuhrBox.getChildren().addAll(zuhr_hourLeft, zuhr_hourRight, time_Separator3, zuhr_minLeft, zuhr_minRight);
        prayertime_pane.setConstraints(zuhrBox, 1, 4);
        prayertime_pane.getChildren().add(zuhrBox);
        
//        zuhr_Label_eng.setMaxSize(100,45);
//        zuhr_Label_eng.setMinSize(100,45);
//        zuhr_Label_eng.setPrefSize(100,45);
//        zuhr_Label_ar.setMaxSize(100,45);
//        zuhr_Label_ar.setMinSize(100,45);
//        zuhr_Label_ar.setPrefSize(100,45);
        
        prayertime_pane.setConstraints(zuhr_Label_eng, 3, 4);
        prayertime_pane.getChildren().add(zuhr_Label_eng);      
        prayertime_pane.setConstraints(zuhr_Label_ar, 3, 4);
        prayertime_pane.getChildren().add(zuhr_Label_ar);
        
        zuhr_hourLeft.setText("-");
        zuhr_hourRight.setText("-");
        zuhr_minLeft.setText("-");
        zuhr_minRight.setText("-");
        time_Separator3.setText(":");

//============================= 
        HBox asrBox = new HBox();
//        asrBox.setSpacing(0);
        asrBox.setMaxSize(80,1);
//        asrBox.setMinSize(100,15);
//        asrBox.setPrefSize(100,15);

        
        if (show_friday)
        
        {
            asr_hourLeft.setId("hourLeft_friday_row");
            asr_hourRight.setId("hourLeft_friday_row");
            time_Separator4.setId("hourLeft_friday_row");
            asr_minLeft.setId("hourLeft_friday_row");
            asr_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            asr_hourLeft.setId("hourLeft");
            asr_hourRight.setId("hourLeft");
            time_Separator4.setId("hourLeft");
            asr_minLeft.setId("hourLeft");
            asr_minRight.setId("hourLeft");
        }
        
        asrBox.getChildren().addAll(asr_hourLeft, asr_hourRight, time_Separator4, asr_minLeft, asr_minRight);
        prayertime_pane.setConstraints(asrBox, 1, 6);
        prayertime_pane.getChildren().add(asrBox);
        
//        asr_Label_eng.setMaxSize(100,40);
//        asr_Label_eng.setMinSize(100,40);
//        asr_Label_eng.setPrefSize(100,40);
//        asr_Label_ar.setMaxSize(100,40);
//        asr_Label_ar.setMinSize(100,40);
//        asr_Label_ar.setPrefSize(100,40);
        
        prayertime_pane.setConstraints(asr_Label_eng, 3, 6);
        prayertime_pane.getChildren().add(asr_Label_eng);      
        prayertime_pane.setConstraints(asr_Label_ar, 3, 6);
        prayertime_pane.getChildren().add(asr_Label_ar);
        
        asr_hourLeft.setText("-");
        asr_hourRight.setText("-");
        asr_minLeft.setText("-");
        asr_minRight.setText("-");
        time_Separator4.setText(":");
        
//============================= 
        
        HBox maghribBox = new HBox();
//        maghribBox.setSpacing(0);
        maghribBox.setMaxSize(80,1);
//        maghribBox.setMinSize(100,15);
//        maghribBox.setPrefSize(100,15);

        
        
        if (show_friday)
        
        {
            maghrib_hourLeft.setId("hourLeft_friday_row");
            maghrib_hourRight.setId("hourLeft_friday_row");
            time_Separator5.setId("hourLeft_friday_row");
            maghrib_minLeft.setId("hourLeft_friday_row");
            maghrib_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            maghrib_hourLeft.setId("hourLeft");
            maghrib_hourRight.setId("hourLeft");
            time_Separator5.setId("hourLeft");
            maghrib_minLeft.setId("hourLeft");
            maghrib_minRight.setId("hourLeft");
        }
        
        
        maghribBox.getChildren().addAll(maghrib_hourLeft, maghrib_hourRight, time_Separator5, maghrib_minLeft, maghrib_minRight);
        prayertime_pane.setConstraints(maghribBox, 1, 8);
        prayertime_pane.getChildren().add(maghribBox);
        
//        maghrib_Label_eng.setMaxSize(100,40);
//        maghrib_Label_eng.setMinSize(100,40);
//        maghrib_Label_eng.setPrefSize(100,40);
//        maghrib_Label_ar.setMaxSize(100,40);
//        maghrib_Label_ar.setMinSize(100,40);
//        maghrib_Label_ar.setPrefSize(100,40);
        
        prayertime_pane.setConstraints(maghrib_Label_eng, 3, 8);
        prayertime_pane.getChildren().add(maghrib_Label_eng);      
        prayertime_pane.setConstraints(maghrib_Label_ar, 3, 8);
        prayertime_pane.getChildren().add(maghrib_Label_ar);
        
        maghrib_hourLeft.setText("-");
        maghrib_hourRight.setText("-");
        maghrib_minLeft.setText("-");
        maghrib_minRight.setText("-");
        time_Separator5.setText(":");

//============================= 
        
        HBox ishaBox = new HBox();
//        ishaBox.setSpacing(0);
        ishaBox.setMaxSize(80,1);
//        ishaBox.setMinSize(100,15);
//        ishaBox.setPrefSize(100,15);
        isha_hourLeft.setId("hourLeft");
        isha_hourRight.setId("hourLeft");
        time_Separator6.setId("hourLeft");
        isha_minLeft.setId("hourLeft");
        isha_minRight.setId("hourLeft");
        
        if (show_friday)
        
        {
            isha_hourLeft.setId("hourLeft_friday_row");
            isha_hourRight.setId("hourLeft_friday_row");
            time_Separator6.setId("hourLeft_friday_row");
            isha_minLeft.setId("hourLeft_friday_row");
            isha_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            isha_hourLeft.setId("hourLeft");
            isha_hourRight.setId("hourLeft");
            time_Separator6.setId("hourLeft");
            isha_minLeft.setId("hourLeft");
            isha_minRight.setId("hourLeft");
        }
        
        
        ishaBox.getChildren().addAll(isha_hourLeft, isha_hourRight, time_Separator6, isha_minLeft, isha_minRight);
        prayertime_pane.setConstraints(ishaBox, 1, 10);
        prayertime_pane.getChildren().add(ishaBox);
        
//        isha_Label_eng.setMaxSize(100,40);
//        isha_Label_eng.setMinSize(100,40);
//        isha_Label_eng.setPrefSize(100,40);
//        isha_Label_ar.setMaxSize(100,40);
//        isha_Label_ar.setMinSize(100,40);
//        isha_Label_ar.setPrefSize(100,40);
        
        prayertime_pane.setConstraints(isha_Label_eng, 3, 10);
        prayertime_pane.getChildren().add(isha_Label_eng);      
        prayertime_pane.setConstraints(isha_Label_ar, 3, 10);
        prayertime_pane.getChildren().add(isha_Label_ar);
        
        isha_hourLeft.setText("-");
        isha_hourRight.setText("-");
        isha_minLeft.setText("-");
        isha_minRight.setText("-");
        time_Separator6.setText(":");
        
        
        //=============================  
         
        if (show_friday)
        
        {
            HBox fridayBox = new HBox();
    //        fridayBox.setSpacing(0);
            fridayBox.setMaxSize(80,1);
    //        fridayBox.setMinSize(100,15);
    //        fridayBox.setPrefSize(100,15);
            
            
            friday_hourLeft.setId("hourLeft_friday_row");
            friday_hourRight.setId("hourLeft_friday_row");
            time_Separator8.setId("hourLeft_friday_row");
            friday_minLeft.setId("hourLeft_friday_row");
            friday_minRight.setId("hourLeft_friday_row");
            
            fridayBox.getChildren().addAll(friday_hourLeft, friday_hourRight, time_Separator8, friday_minLeft, friday_minRight);
            prayertime_pane.setConstraints(fridayBox, 0, 13);
            prayertime_pane.getChildren().add(fridayBox);

    //        friday_Label_eng.setMaxSize(100,40);
    //        friday_Label_eng.setMinSize(100,40);
    //        friday_Label_eng.setPrefSize(100,40);
    //        friday_Label_ar.setMaxSize(100,40);
    //        friday_Label_ar.setMinSize(100,40);
    //        friday_Label_ar.setPrefSize(100,40);

            prayertime_pane.setConstraints(friday_Label_eng, 3, 13);
            prayertime_pane.getChildren().add(friday_Label_eng);
            prayertime_pane.setConstraints(friday_Label_ar, 3, 13);
            prayertime_pane.getChildren().add(friday_Label_ar);

            friday_hourLeft.setText("-");
            friday_hourRight.setText("-");
            friday_minLeft.setText("-");
            friday_minRight.setText("-");
            time_Separator8.setText(":");

            final Separator sepHor5 = new Separator();
            prayertime_pane.setValignment(sepHor5,VPos.CENTER);
            prayertime_pane.setConstraints(sepHor5, 0, 12);
            prayertime_pane.setColumnSpan(sepHor5, 3);
//            sepHor5.setMinHeight(1);
            prayertime_pane.getChildren().add(sepHor5);
        
        }
 //============================= 
        
        final Separator sepHor1 = new Separator();
        prayertime_pane.setValignment(sepHor1,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor1, 0, 3);
        prayertime_pane.setColumnSpan(sepHor1, 3);
//        sepHor1.setMaxSize(100,15);
//        sepHor1.setMinSize(100,15);
//        sepHor1.setMinHeight(1);
        prayertime_pane.getChildren().add(sepHor1);   
        
        final Separator sepHor2 = new Separator();
        prayertime_pane.setValignment(sepHor2,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor2, 0, 5);
        prayertime_pane.setColumnSpan(sepHor2, 3);
//        sepHor2.setMinHeight(1);
        prayertime_pane.getChildren().add(sepHor2);
        
        final Separator sepHor3 = new Separator();
        prayertime_pane.setValignment(sepHor3,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor3, 0, 7);
        prayertime_pane.setColumnSpan(sepHor3, 3);
//        sepHor3.setMinHeight(1);
        prayertime_pane.getChildren().add(sepHor3);
        
        final Separator sepHor4 = new Separator();
        prayertime_pane.setValignment(sepHor4,VPos.CENTER);
        prayertime_pane.setConstraints(sepHor4, 0, 9);
        prayertime_pane.setColumnSpan(sepHor4, 3);
//        sepHor4.setMinHeight(1);
        prayertime_pane.getChildren().add(sepHor4);
        
        
        
        

//========= Jamama=======
               
//=============================  
        HBox fajr_jamma_Box = new HBox();
//        fajr_jamma_Box.setSpacing(0);
        fajr_jamma_Box.setMaxSize(80,1);
//        fajr_jamma_Box.setMinSize(100,40);
//        fajr_jamma_Box.setPrefSize(100,15);

        
        if (show_friday)
        
        {
            fajr_jamma_hourLeft.setId("hourLeft_friday_row");
            fajr_jamma_hourRight.setId("hourLeft_friday_row");
            time_jamma_Separator1.setId("hourLeft_friday_row");
            fajr_jamma_minLeft.setId("hourLeft_friday_row");
            fajr_jamma_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            fajr_jamma_hourLeft.setId("hourLeft");
            fajr_jamma_hourRight.setId("hourLeft");
            time_jamma_Separator1.setId("hourLeft");
            fajr_jamma_minLeft.setId("hourLeft");
            fajr_jamma_minRight.setId("hourLeft");
        }
        
        
        fajr_jamma_Box.getChildren().addAll(fajr_jamma_hourLeft, fajr_jamma_hourRight, time_jamma_Separator1, fajr_jamma_minLeft, fajr_jamma_minRight);
        prayertime_pane.setConstraints(fajr_jamma_Box, 0, 2);
        prayertime_pane.getChildren().add(fajr_jamma_Box);
        
        fajr_jamma_hourLeft.setText("-");
        fajr_jamma_hourRight.setText("-");
        fajr_jamma_minLeft.setText("-");
        fajr_jamma_minRight.setText("-");
        time_jamma_Separator1.setText(":");
        
//============================= 
        HBox zuhr_jamma_Box = new HBox();
//        zuhr_jamma_Box.setSpacing(0);
        zuhr_jamma_Box.setMaxSize(80,1);
//        zuhr_jamma_Box.setMinSize(100,15);
//        zuhr_jamma_Box.setPrefSize(100,15);

        
        if (show_friday)
        
        {
            zuhr_jamma_hourLeft.setId("hourLeft_friday_row");
            zuhr_jamma_hourRight.setId("hourLeft_friday_row");
            time_jamma_Separator2.setId("hourLeft_friday_row");
            zuhr_jamma_minLeft.setId("hourLeft_friday_row");
            zuhr_jamma_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            zuhr_jamma_hourLeft.setId("hourLeft");
            zuhr_jamma_hourRight.setId("hourLeft");
            time_jamma_Separator2.setId("hourLeft");
            zuhr_jamma_minLeft.setId("hourLeft");
            zuhr_jamma_minRight.setId("hourLeft");
        }
        
        
        zuhr_jamma_Box.getChildren().addAll(zuhr_jamma_hourLeft, zuhr_jamma_hourRight, time_jamma_Separator2, zuhr_jamma_minLeft, zuhr_jamma_minRight);
        prayertime_pane.setConstraints(zuhr_jamma_Box, 0, 4);
        prayertime_pane.getChildren().add(zuhr_jamma_Box);
        
        zuhr_jamma_hourLeft.setText("-");
        zuhr_jamma_hourRight.setText("-");
        zuhr_jamma_minLeft.setText("-");
        zuhr_jamma_minRight.setText("-");
        time_jamma_Separator2.setText(":");

//============================= 
        HBox asr_jamma_Box = new HBox();
//        asr_jamma_Box.setSpacing(0);
        asr_jamma_Box.setMaxSize(80,1);
//        asr_jamma_Box.setMinSize(100,15);
//        asr_jamma_Box.setPrefSize(100,15);
 
        
        if (show_friday)
        
        {
            asr_jamma_minRight.setId("hourLeft_friday_row");
            asr_jamma_minLeft.setId("hourLeft_friday_row");
            time_jamma_Separator3.setId("hourLeft_friday_row");
            asr_jamma_hourRight.setId("hourLeft_friday_row");
            asr_jamma_hourLeft.setId("hourLeft_friday_row");   
        }
        
        else
        {
            asr_jamma_minRight.setId("hourLeft");
            asr_jamma_minLeft.setId("hourLeft");
            time_jamma_Separator3.setId("hourLeft");
            asr_jamma_hourRight.setId("hourLeft");
            asr_jamma_hourLeft.setId("hourLeft");
        }
        
        
        asr_jamma_Box.getChildren().addAll(asr_jamma_hourLeft, asr_jamma_hourRight, time_jamma_Separator3, asr_jamma_minLeft, asr_jamma_minRight);
        prayertime_pane.setConstraints(asr_jamma_Box, 0, 6);
        prayertime_pane.getChildren().add(asr_jamma_Box);
        
        asr_jamma_hourLeft.setText("-");
        asr_jamma_hourRight.setText("-");
        asr_jamma_minLeft.setText("-");
        asr_jamma_minRight.setText("-");
        time_jamma_Separator3.setText(":");
        
//============================= 
        
        HBox maghrib_jamma_Box = new HBox();
//        maghrib_jamma_Box.setSpacing(0);
        maghrib_jamma_Box.setMaxSize(80,1);
//        maghrib_jamma_Box.setMinSize(100,15);
//        maghrib_jamma_Box.setPrefSize(100,15);

        if (show_friday)
        
        {
            maghrib_jamma_minRight.setId("hourLeft_friday_row");
            maghrib_jamma_minLeft.setId("hourLeft_friday_row");
            time_jamma_Separator4.setId("hourLeft_friday_row");
            maghrib_jamma_hourRight.setId("hourLeft_friday_row");
            maghrib_jamma_hourLeft.setId("hourLeft_friday_row");   
        }
        
        else
        {
            maghrib_jamma_minRight.setId("hourLeft");
            maghrib_jamma_minLeft.setId("hourLeft");
            time_jamma_Separator4.setId("hourLeft");
            maghrib_jamma_hourRight.setId("hourLeft");
            maghrib_jamma_hourLeft.setId("hourLeft");
        }
        
        
        maghrib_jamma_Box.getChildren().addAll(maghrib_jamma_hourLeft, maghrib_jamma_hourRight, time_jamma_Separator4, maghrib_jamma_minLeft, maghrib_jamma_minRight);
        prayertime_pane.setConstraints(maghrib_jamma_Box, 0, 8);
        prayertime_pane.getChildren().add(maghrib_jamma_Box);
        
        maghrib_jamma_hourLeft.setText("-");
        maghrib_jamma_hourRight.setText("-");
        maghrib_jamma_minLeft.setText("-");
        maghrib_jamma_minRight.setText("-");
        time_jamma_Separator4.setText(":");
//============================= 
        
        HBox isha_jamma_Box = new HBox();
//        isha_jamma_Box.setSpacing(0);
        isha_jamma_Box.setMaxSize(80,1);
//        isha_jamma_Box.setMinSize(100,15);
//        isha_jamma_Box.setPrefSize(100,15);

        
        
        if (show_friday)
        
        {
            isha_jamma_hourLeft.setId("hourLeft_friday_row");
            isha_jamma_hourRight.setId("hourLeft_friday_row");
            time_jamma_Separator5.setId("hourLeft_friday_row");
            isha_jamma_minLeft.setId("hourLeft_friday_row");
            isha_jamma_minRight.setId("hourLeft_friday_row");   
        }
        
        else
        {
            isha_jamma_hourLeft.setId("hourLeft");
            isha_jamma_hourRight.setId("hourLeft");
            time_jamma_Separator5.setId("hourLeft");
            isha_jamma_minLeft.setId("hourLeft");
            isha_jamma_minRight.setId("hourLeft");
        }
        
        
        isha_jamma_Box.getChildren().addAll(isha_jamma_hourLeft, isha_jamma_hourRight, time_jamma_Separator5, isha_jamma_minLeft, isha_jamma_minRight);
        prayertime_pane.setConstraints(isha_jamma_Box, 0, 10);
        prayertime_pane.getChildren().add(isha_jamma_Box);
        
        isha_jamma_hourLeft.setText("-");
        isha_jamma_hourRight.setText("-");
        isha_jamma_minLeft.setText("-");
        isha_jamma_minRight.setText("-");
        time_jamma_Separator5.setText(":");
        
        if(show_friday && double_friday)
            
        {
            HBox fridayBox2 = new HBox();
            fridayBox2.setMaxSize(80,1);
            friday2_hourLeft.setId("hourLeft_friday_row");
            friday2_hourRight.setId("hourLeft_friday_row");
            time_Separator9.setId("hourLeft_friday_row");
            friday2_minLeft.setId("hourLeft_friday_row");
            friday2_minRight.setId("hourLeft_friday_row");
            fridayBox2.getChildren().addAll(friday2_hourLeft, friday2_hourRight, time_Separator9, friday2_minLeft, friday2_minRight);
            prayertime_pane.setConstraints(fridayBox2, 1, 13);
            prayertime_pane.getChildren().add(fridayBox2);

            friday2_hourLeft.setText("-");
            friday2_hourRight.setText("-");
            friday2_minLeft.setText("-");
            friday2_minRight.setText("-");
            time_Separator9.setText(":");
        
        
        }
       
   }

    return prayertime_pane;
} 
    
//===MOON PANE==========================  
    public GridPane moonpane() {
      
        GridPane Moonpane = new GridPane();
        if (orientation.equals("vertical") )
        {
            Moonpane.setId("moonpane");
            Moonpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().prefWidth(220).minWidth(220).build(),
                    ColumnConstraintsBuilder.create().prefWidth(100).minWidth(100).build()     
            );
            Moonpane.setHgap(40);
            Moonpane.setMaxHeight(50);
    //       Moonpane.setGridLinesVisible(false);

            ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/100%.png")));      
            Moon_img.setFitWidth(120);
            Moon_img.setFitHeight(120);
    //        Moon_img.setPreserveRatio(false);
            Moon_img.setSmooth(true);
            Moon_Image_Label.setGraphic(Moon_img);
            Moonpane.setConstraints(Moon_Image_Label, 1, 0);
            Moonpane.getChildren().add(Moon_Image_Label); 
            Moon_Date_Label.setId("moon-text-english");
            Moon_Date_Label.setWrapText(true);
            Moon_Date_Label.setText("Loading......");
            Moonpane.setConstraints(Moon_Date_Label, 0, 0);
            Moonpane.getChildren().add(Moon_Date_Label);

        }
        else if (orientation.equals("horizontal"))
            
        {
            Moonpane.setId("moonpane");
            Moonpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().prefWidth(140).minWidth(140).build(),
                    ColumnConstraintsBuilder.create().prefWidth(100).minWidth(60).build()     
            );
            Moonpane.setHgap(10);
            Moonpane.setMaxHeight(50);
    //       Moonpane.setGridLinesVisible(false);

            ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/100%.png")));      
            Moon_img.setFitWidth(50);
            Moon_img.setFitHeight(50);
    //        Moon_img.setPreserveRatio(false);
            Moon_img.setSmooth(true);
            Moon_Image_Label.setGraphic(Moon_img);
            Moonpane.setConstraints(Moon_Image_Label, 1, 0);
            Moonpane.getChildren().add(Moon_Image_Label); 
            Moon_Date_Label.setId("moon-text-english");
            Moon_Date_Label.setWrapText(true);
            Moon_Date_Label.setText("Loading......");
            Moonpane.setConstraints(Moon_Date_Label, 0, 0);
            Moonpane.getChildren().add(Moon_Date_Label);
            
        }
        
        else if (orientation.equals("camera_mode"))
            
        {
            Moonpane.setId("moonpane");
            Moonpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().prefWidth(140).minWidth(140).build(),
                    ColumnConstraintsBuilder.create().prefWidth(100).minWidth(60).build()     
            );
            Moonpane.setHgap(10);
            Moonpane.setMaxHeight(50);
    //       Moonpane.setGridLinesVisible(false);

            ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/100%.png")));      
            Moon_img.setFitWidth(50);
            Moon_img.setFitHeight(50);
    //        Moon_img.setPreserveRatio(false);
            Moon_img.setSmooth(true);
            Moon_Image_Label.setGraphic(Moon_img);
            Moonpane.setConstraints(Moon_Image_Label, 1, 0);
            Moonpane.getChildren().add(Moon_Image_Label); 
            Moon_Date_Label.setId("moon-text-english");
            Moon_Date_Label.setWrapText(true);
            Moon_Date_Label.setText("Loading......");
            Moonpane.setConstraints(Moon_Date_Label, 0, 0);
            Moonpane.getChildren().add(Moon_Date_Label);
            
        }
        
        else
            
        {
            Moonpane.setId("moonpane");
            Moonpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().minWidth(350).build(),
//                    ColumnConstraintsBuilder.create().minWidth(50).build(),
                    ColumnConstraintsBuilder.create().minWidth(160).build()     
            );
            Moonpane.setHgap(30);
            Moonpane.setMaxHeight(50);
//           Moonpane.setGridLinesVisible(true);

            ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/100%.png")));      
            Moon_img.setFitWidth(120);
            Moon_img.setFitHeight(120);
    //        Moon_img.setPreserveRatio(false);
            Moon_img.setSmooth(true);
            Moon_Image_Label.setGraphic(Moon_img);
            Moonpane.setConstraints(Moon_Image_Label, 1, 0);
            Moonpane.getChildren().add(Moon_Image_Label); 
            Moon_Date_Label.setId("moon-text-english");
            Moon_Date_Label.setWrapText(true);
            Moon_Date_Label.setText("Loading......");
            Moonpane.setConstraints(Moon_Date_Label, 0, 0);
            Moonpane.getChildren().add(Moon_Date_Label);
            
        }
        
        return Moonpane;
    }
    
//===MOON PANE==========================  
    public GridPane weatherpane() {
      
        GridPane Weatherpane = new GridPane();
        if (orientation.equals("vertical") )
        {
            Weatherpane.setId("moonpane");
            Weatherpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().prefWidth(220).minWidth(220).build(),
                    ColumnConstraintsBuilder.create().prefWidth(100).minWidth(100).build()     
            );
            Weatherpane.setHgap(40);
            Weatherpane.setMaxHeight(50);
    //       Moonpane.setGridLinesVisible(false);

            ImageView Moon_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Moon/100%.png")));      
            Moon_img.setFitWidth(120);
            Moon_img.setFitHeight(120);
    //        Moon_img.setPreserveRatio(false);
            Moon_img.setSmooth(true);
            Moon_Image_Label.setGraphic(Moon_img);
            Weatherpane.setConstraints(Moon_Image_Label, 1, 0);
            Weatherpane.getChildren().add(Moon_Image_Label); 
            Moon_Date_Label.setId("moon-text-english");
            Moon_Date_Label.setWrapText(true);
            Moon_Date_Label.setText("Loading......");
            Weatherpane.setConstraints(Moon_Date_Label, 0, 0);
            Weatherpane.getChildren().add(Moon_Date_Label);

        }
        else if (orientation.equals("horizontal"))
            
        {
            Weatherpane.setId("moonpane");
            Weatherpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().minWidth(70).build(),
                    ColumnConstraintsBuilder.create().minWidth(50).build()     
            );
            Weatherpane.getRowConstraints().setAll(
                    RowConstraintsBuilder.create().minHeight(30).build(),
                    RowConstraintsBuilder.create().minHeight(10).build()     
            );
            Weatherpane.setHgap(10);
            Weatherpane.setMaxHeight(50);
//           Weatherpane.setGridLinesVisible(true);
            weather_img.setFitWidth(50);
            weather_img.setFitHeight(50);
            weather_img.setPreserveRatio(true);
//            weather_img.setSmooth(true);
            
            Weather_Image_Label.setGraphic(weather_img);
            Weatherpane.setConstraints(Weather_Image_Label, 1, 0,1,2);
            Weatherpane.getChildren().add(Weather_Image_Label); 
            Weatherpane.setValignment(Weather_Image_Label,VPos.CENTER);
            Weatherpane.setHalignment(Weather_Image_Label,HPos.CENTER);
            Weather_Label1.setId("Weather_Label1");
            Weather_Label1.setText("Loading......");
            Weatherpane.setValignment(Weather_Label1,VPos.CENTER);
            Weatherpane.setHalignment(Weather_Label1,HPos.CENTER);
            Weatherpane.setConstraints(Weather_Label1, 0, 0,1,1);
            Weatherpane.getChildren().add(Weather_Label1);
            
            Weather_Label2.setId("Weather_Label2");
            Weather_Label2.setText("Loading......");
            Weatherpane.setValignment(Weather_Label2,VPos.CENTER);
            Weatherpane.setHalignment(Weather_Label2,HPos.CENTER);
            Weatherpane.setConstraints(Weather_Label2, 0, 1,1,1);
            Weatherpane.getChildren().add(Weather_Label2);
            
            
            
        }
        
        else if (orientation.equals("camera_mode"))
            
        {
            Weatherpane.setId("moonpane");
            Weatherpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().minWidth(50).build(),
                    ColumnConstraintsBuilder.create().minWidth(50).build()     
            );
            Weatherpane.getRowConstraints().setAll(
                    RowConstraintsBuilder.create().minHeight(30).build(),
                    RowConstraintsBuilder.create().minHeight(10).build()     
            );
            Weatherpane.setHgap(0);
            Weatherpane.setMaxHeight(50);
//           Weatherpane.setGridLinesVisible(true);
            weather_img.setFitWidth(35);
            weather_img.setFitHeight(35);
            weather_img.setPreserveRatio(true);
//            weather_img.setSmooth(true);
            
            Weather_Image_Label.setGraphic(weather_img);
            Weatherpane.setConstraints(Weather_Image_Label, 1, 0,1,2);
            Weatherpane.getChildren().add(Weather_Image_Label); 
            Weatherpane.setValignment(Weather_Image_Label,VPos.CENTER);
            Weatherpane.setHalignment(Weather_Image_Label,HPos.CENTER);
            Weather_Label1.setId("Weather_Label1");
            Weather_Label1.setText("Loading......");
            Weatherpane.setValignment(Weather_Label1,VPos.CENTER);
            Weatherpane.setHalignment(Weather_Label1,HPos.CENTER);
            Weatherpane.setConstraints(Weather_Label1, 0, 0,1,1);
            Weatherpane.getChildren().add(Weather_Label1);
            
            Weather_Label2.setId("Weather_Label2");
            Weather_Label2.setText("Loading......");
            Weatherpane.setValignment(Weather_Label2,VPos.CENTER);
            Weatherpane.setHalignment(Weather_Label2,HPos.CENTER);
            Weatherpane.setConstraints(Weather_Label2, 0, 1,1,1);
            Weatherpane.getChildren().add(Weather_Label2);
            
            
            
        }
        
        else
            
        {
            Weatherpane.setId("moonpane");
            Weatherpane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().minWidth(150).build(),
                    ColumnConstraintsBuilder.create().minWidth(160).build()     
            );
            Weatherpane.getRowConstraints().setAll(
                    RowConstraintsBuilder.create().minHeight(70).build(),
                    RowConstraintsBuilder.create().minHeight(30).build()     
            );
            Weatherpane.setHgap(20);
//            Weatherpane.setMaxHeight(50);
//           Weatherpane.setGridLinesVisible(true);
            weather_img.setFitWidth(160);
            weather_img.setFitHeight(160);
    //        Moon_img.setPreserveRatio(false);
//            weather_img.setSmooth(true);
            
            Weather_Image_Label.setGraphic(weather_img);
            Weatherpane.setConstraints(Weather_Image_Label, 1, 0,1,2);
            Weatherpane.getChildren().add(Weather_Image_Label); 
            Weather_Label1.setId("Weather_Label1");
            Weather_Label1.setText("Loading......");
            Weatherpane.setValignment(Weather_Label1,VPos.CENTER);
            Weatherpane.setHalignment(Weather_Label1,HPos.CENTER);
            Weatherpane.setConstraints(Weather_Label1, 0, 0,1,1);
            Weatherpane.getChildren().add(Weather_Label1);
            
            Weather_Label2.setId("Weather_Label2");
            Weather_Label2.setText("Loading......");
            Weatherpane.setValignment(Weather_Label2,VPos.CENTER);
            Weatherpane.setHalignment(Weather_Label2,HPos.CENTER);
            Weatherpane.setConstraints(Weather_Label2, 0, 1,1,1);
            Weatherpane.getChildren().add(Weather_Label2);
        }
        
        return Weatherpane;
    }
//===SUN RISE PANE==========================  
    public GridPane sunrise() {
      
        GridPane Sunrise = new GridPane();
        Sunrise.setId("moonpane");
        
        if (orientation.equals("vertical") )
        {
            Sunrise.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().prefWidth(220).minWidth(220).build(),
                    ColumnConstraintsBuilder.create().prefWidth(100).minWidth(100).build()     
            );
            Sunrise.setHgap(40);
            Sunrise.setMaxHeight(50);
    //       Moonpane.setGridLinesVisible(false);

            ImageView Sunrise_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Sun/Sun.png")));      
            Sunrise_img.setFitWidth(160);
            Sunrise_img.setFitHeight(160);
    //        Moon_img.setPreserveRatio(false);
            Sunrise_img.setSmooth(true);
            Sunrise_Image_Label.setGraphic(Sunrise_img);
            Sunrise.setConstraints(Sunrise_Image_Label, 1, 0);
            Sunrise.getChildren().add(Sunrise_Image_Label); 
            Sunrise_Date_Label.setId("sunrise-text-english");
            Sunrise_Date_Label.setWrapText(true);
            Sunrise_Date_Label.setText("Loading......");
            Sunrise.setConstraints(Sunrise_Date_Label, 0, 0);
            Sunrise.getChildren().add(Sunrise_Date_Label);
        }
        
        else if (orientation.equals("horizontal"))
            
        {
            Sunrise.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().prefWidth(120).minWidth(120).build(),
                    ColumnConstraintsBuilder.create().prefWidth(80).minWidth(80).build()     
            );
            Sunrise.setHgap(8);
            Sunrise.setMaxHeight(50);
    //       Moonpane.setGridLinesVisible(false);

            ImageView Sunrise_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Sun/Sun.png")));      
            Sunrise_img.setFitWidth(75);
            Sunrise_img.setFitHeight(75);
    //        Moon_img.setPreserveRatio(false);
            Sunrise_img.setSmooth(true);
            Sunrise_Image_Label.setGraphic(Sunrise_img);
            Sunrise.setConstraints(Sunrise_Image_Label, 1, 0);
            Sunrise.getChildren().add(Sunrise_Image_Label); 
            Sunrise_Date_Label.setId("sunrise-text-english");
            Sunrise_Date_Label.setWrapText(true);
            Sunrise_Date_Label.setText("Loading......");
            Sunrise.setConstraints(Sunrise_Date_Label, 0, 0);
            Sunrise.getChildren().add(Sunrise_Date_Label);
        }
        
        else if (orientation.equals("camera_mode"))
            
        {
            Sunrise.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().prefWidth(120).minWidth(120).build(),
                    ColumnConstraintsBuilder.create().prefWidth(80).minWidth(80).build()     
            );
            Sunrise.setHgap(8);
            Sunrise.setMaxHeight(50);
    //       Moonpane.setGridLinesVisible(false);

            ImageView Sunrise_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Sun/Sun.png")));      
            Sunrise_img.setFitWidth(50);
            Sunrise_img.setFitHeight(50);
    //        Moon_img.setPreserveRatio(false);
            Sunrise_img.setSmooth(true);
            Sunrise_Image_Label.setGraphic(Sunrise_img);
            Sunrise.setConstraints(Sunrise_Image_Label, 1, 0);
            Sunrise.getChildren().add(Sunrise_Image_Label); 
            Sunrise_Date_Label.setId("sunrise-text-english");
            Sunrise_Date_Label.setWrapText(true);
            Sunrise_Date_Label.setText("Loading......");
            Sunrise.setConstraints(Sunrise_Date_Label, 0, 0);
            Sunrise.getChildren().add(Sunrise_Date_Label);
        }
        
        else
            
        {
            Sunrise.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().minWidth(350).build(),
//                    ColumnConstraintsBuilder.create().minWidth(50).build() ,
                    ColumnConstraintsBuilder.create().minWidth(150).build()    
            );
            Sunrise.setHgap(30);
            Sunrise.setMaxHeight(50);
//           Sunrise.setGridLinesVisible(true);

            ImageView Sunrise_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Sun/Sun.png")));      
            Sunrise_img.setFitWidth(150);
            Sunrise_img.setFitHeight(150);
    //        Moon_img.setPreserveRatio(false);
            Sunrise_img.setSmooth(true);
            Sunrise_Image_Label.setGraphic(Sunrise_img);
            Sunrise.setConstraints(Sunrise_Image_Label, 1, 0);
            Sunrise.getChildren().add(Sunrise_Image_Label); 
            Sunrise_Date_Label.setId("sunrise-text-english");
            Sunrise_Date_Label.setWrapText(true);
            Sunrise_Date_Label.setText("Loading......");
            Sunrise.setConstraints(Sunrise_Date_Label, 0, 0);
            Sunrise.getChildren().add(Sunrise_Date_Label);
        }

        return Sunrise;
    }
    
    
//===hadith PANE==========================  
    public GridPane hadithPane() {
      
        GridPane hadithPane = new GridPane();
        
        hadithPane.getRowConstraints().setAll(
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/10.0).build()
            );
        
     
       hadithPane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100).build()
                   
   
        );
//        hadithPane.setGridLinesVisible(true);
        
        
        if (!show_friday){hadithPane.setId("hadithpane");}
        else {hadithPane.setId("hadithpane_friday"); System.out.println("hadith pane using friday css");}
        
        
        hadithPane.setVgap(0);

        hadith_Label.setId("hadith-text-arabic");
        hadith_Label.setWrapText(true);
        hadith_Label.setMinHeight(0);
        hadithPane.setConstraints(hadith_Label, 0, 0,1,10);
        hadithPane.getChildren().add(hadith_Label);
        
        
        en_moon_hadith_Label_L1.setId("en_moon-notification-text1");
        en_moon_hadith_Label_L1.setWrapText(true);
        en_moon_hadith_Label_L1.setText("Loading.....");
        en_moon_hadith_Label_L1.setMinHeight(0);
        hadithPane.setConstraints(en_moon_hadith_Label_L1, 0, 0,1,5);
        hadithPane.getChildren().add(en_moon_hadith_Label_L1);
        
        en_moon_hadith_Label_L2.setWrapText(true);
        en_moon_hadith_Label_L2.setMinHeight(0);
        hadithPane.setConstraints(en_moon_hadith_Label_L2, 0, 5,1,5);
        hadithPane.getChildren().add(en_moon_hadith_Label_L2);
        
        ar_moon_hadith_Label_L1.setId("ar_moon-notification-text1");
        ar_moon_hadith_Label_L1.setWrapText(true);
        ar_moon_hadith_Label_L1.setMinHeight(0);
        hadithPane.setConstraints(ar_moon_hadith_Label_L1, 0, 0,1,5);
        hadithPane.getChildren().add(ar_moon_hadith_Label_L1);
        
        ar_moon_hadith_Label_L2.setWrapText(true);
        ar_moon_hadith_Label_L2.setMinHeight(0);
        hadithPane.setConstraints(ar_moon_hadith_Label_L2, 0, 5,1,5);
        hadithPane.getChildren().add(ar_moon_hadith_Label_L2);
        
//        if (orientation.equals("horizontal_HD") )
//        {
//            ar_moon_hadith_Label_L1.setTranslateY(35);
//            ar_moon_hadith_Label_L2.setTranslateY(30);
//        }

        
        facebook_Label.setWrapText(true);
        facebook_Label.setAlignment(Pos.CENTER);
        hadithPane.setConstraints(facebook_Label, 0, 0,1,10);
        hadithPane.getChildren().add(facebook_Label);
        
//        athan_Change_Label_L1.setWrapText(true);
//        athan_Change_Label_L1.setMinHeight(0);
//        hadithPane.setConstraints(athan_Change_Label_L1, 0, 3);
//        hadithPane.getChildren().add(athan_Change_Label_L1);
//        
//        athan_Change_Label_L2.setWrapText(true);
//        athan_Change_Label_L2.setMinHeight(0);
//        hadithPane.setConstraints(athan_Change_Label_L2, 0, 4);
//        hadithPane.getChildren().add(athan_Change_Label_L2);

        return hadithPane;
    }    

//===Footer PANE==========================  

    public GridPane footerPane() {
      
        GridPane footerPane = new GridPane();
//        hadithPane.setGridLinesVisible(false);  
        double size = 10;
        TextFlow textFlow1 = new TextFlow();
        TextFlow textFlow2 = new TextFlow();
        TextFlow textFlow3 = new TextFlow();
        Text text1 = new Text("                                           Get prayer time notifications and daily hadith on your mobile by following us on ");
        text1.setFont(Font.font("Tahoma", size));
        text1.setFill(Color.WHITE);
        ImageView facebook_image = new ImageView(new Image(getClass().getResourceAsStream("/Images/facebook.png")));
        facebook_image.setTranslateY(15);
        Text text5 = new Text("  or  ");
        text5.setFont(Font.font("Tahoma", size));
        text5.setFill(Color.WHITE);
        ImageView twitter_image = new ImageView(new Image(getClass().getResourceAsStream("/Images/twitter.png")));
        Text text6 = new Text("                                                                 Twitter: ");
        text6.setFont(Font.font("Tahoma", size));
        text6.setFill(Color.WHITE);
        Text text7 = new Text("@Daar_Ibn_Abbas");
        text7.setFont(Font.font("Tahoma", FontWeight.BOLD, size));
        text7.setFill(Color.WHITE);
        Text text8 = new Text("           Facebook Page: ");
        text8.setFont(Font.font("Tahoma", size));
        text8.setFill(Color.WHITE);
        Text text9 = new Text("Daar-Ibn-Abbas");
        text9.setFont(Font.font("Tahoma" , FontWeight.BOLD, size));
        text9.setFill(Color.WHITE);
        Text text10 = new Text("لا حول ولا قوة الا بالله العلي العظيم                                                                                             ");
        text10.setFont(Font.font("Tahoma", size));
        text10.setFill(Color.GOLDENROD);
//        
//        
//        ImageView twitter_code = new ImageView(new Image(getClass().getResourceAsStream("/Images/QR_CODE_Twitter.png"))); 
//        twitter_code.setFitHeight(70);
//        twitter_code.setTranslateY(20);
//        twitter_code.setPreserveRatio(true);
//        ImageView facebook_code = new ImageView(new Image(getClass().getResourceAsStream("/Images/QR_CODE_Facebook.png")));
//        facebook_code.setFitHeight(70);
//        facebook_code.setTranslateY(20);
//        facebook_code.setPreserveRatio(true);
        textFlow1.getChildren().addAll(text1, twitter_image, text5, facebook_image);
        footerPane.setHalignment(textFlow1,HPos.CENTER);
        footerPane.setConstraints(textFlow1, 0, 0);
        footerPane.getChildren().add(textFlow1);
        
        textFlow2.getChildren().addAll(text6,text7,text8, text9);
        footerPane.setHalignment(textFlow2,HPos.CENTER);
        footerPane.setConstraints(textFlow2, 0, 1);
        footerPane.getChildren().add(textFlow2);
        
        textFlow3.getChildren().addAll(text10);
        footerPane.setHalignment(textFlow3,HPos.CENTER);
        footerPane.setConstraints(textFlow3, 0, 2);
        footerPane.getChildren().add(textFlow3);

        return footerPane;
    }    

//===Clock PANE==========================  
    
    public GridPane clockPane() {
      
        GridPane clockPane = new GridPane();
        clockPane.setId("clockPane");
//        clockPane.setVgap(30);
        
        if (orientation.equals("vertical") )
        {
            clockPane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().minWidth(10).maxWidth(50).build(),
                ColumnConstraintsBuilder.create().minWidth(120).maxWidth(300).build(),
                ColumnConstraintsBuilder.create().minWidth(40).maxWidth(50).build(),
                ColumnConstraintsBuilder.create().minWidth(120).maxWidth(160).build(),
                ColumnConstraintsBuilder.create().minWidth(70).maxWidth(150).build()    
            );

            clockPane.setHgap(0);
    //        clockPane.setGridLinesVisible(true);
    //        clockPane.setMaxHeight(50);

            hour_Label.setId("hour");  
            clockPane.setHalignment(hour_Label,HPos.RIGHT);
            clockPane.setConstraints(hour_Label, 1, 0);
            clockPane.getChildren().add(hour_Label);

            separator_Label.setText(":");
            separator_Label.setId("clock_separator");  
            clockPane.setHalignment(separator_Label,HPos.CENTER);
            clockPane.setConstraints(separator_Label, 2, 0);
            clockPane.getChildren().add(separator_Label);

            minute_Label.setId("minute");  
            clockPane.setHalignment(minute_Label,HPos.CENTER);
            clockPane.setValignment(minute_Label,VPos.CENTER);
            clockPane.setConstraints(minute_Label, 3, 0);
            clockPane.getChildren().add(minute_Label);

            second_Label.setId("second");  
            clockPane.setHalignment(second_Label,HPos.CENTER);
            clockPane.setValignment(second_Label,VPos.CENTER);
            clockPane.setConstraints(second_Label, 4, 0);
            clockPane.getChildren().add(second_Label);

            date_Label.setId("date");
            clockPane.setHalignment(date_Label,HPos.CENTER);
            clockPane.setConstraints(date_Label, 0, 1,5,1);
            clockPane.getChildren().add(date_Label);

        
        }
        
        else if (orientation.equals("horizontal") )
        {
            clockPane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().minWidth(10).maxWidth(70).build(),
                ColumnConstraintsBuilder.create().minWidth(50).maxWidth(90).build(),
                ColumnConstraintsBuilder.create().minWidth(25).maxWidth(70).build(),
                ColumnConstraintsBuilder.create().minWidth(40).maxWidth(90).build(),
                ColumnConstraintsBuilder.create().minWidth(50).maxWidth(70).build()    
            );

            clockPane.getRowConstraints().setAll(
                    RowConstraintsBuilder.create().minHeight(10).build(),
                    RowConstraintsBuilder.create().minHeight(20).build(),
                    RowConstraintsBuilder.create().minHeight(67).build(),
                    RowConstraintsBuilder.create().minHeight(10).build()
            );

            clockPane.setHgap(0);
    //        clockPane.setGridLinesVisible(true);
    //        clockPane.setMaxHeight(50);

            hour_Label.setId("hour");  
            clockPane.setHalignment(hour_Label,HPos.RIGHT);
            clockPane.setConstraints(hour_Label, 1, 2, 1, 3);
            clockPane.getChildren().add(hour_Label);

            separator_Label.setText(":");
            separator_Label.setId("clock_separator");  
            clockPane.setHalignment(separator_Label,HPos.CENTER);
            clockPane.setConstraints(separator_Label, 2, 2, 1, 3);
            clockPane.getChildren().add(separator_Label);

            minute_Label.setId("minute");  
            clockPane.setHalignment(minute_Label,HPos.CENTER);
            clockPane.setValignment(minute_Label,VPos.CENTER);
            clockPane.setConstraints(minute_Label, 3, 2, 1, 3);
            clockPane.getChildren().add(minute_Label);

            second_Label.setId("second");  
            clockPane.setHalignment(second_Label,HPos.CENTER);
            clockPane.setValignment(second_Label,VPos.CENTER);
            clockPane.setConstraints(second_Label, 4, 2, 1, 3);
            clockPane.getChildren().add(second_Label);

            date_Label.setId("date");
            clockPane.setHalignment(date_Label,HPos.CENTER);
            clockPane.setConstraints(date_Label, 1, 3,5,1);
            clockPane.getChildren().add(date_Label);

        }
        
        else if (orientation.equals("camera_mode") )
        {
            clockPane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().minWidth(10).maxWidth(70).build(),
                ColumnConstraintsBuilder.create().minWidth(50).maxWidth(90).build(),
                ColumnConstraintsBuilder.create().minWidth(25).maxWidth(70).build(),
                ColumnConstraintsBuilder.create().minWidth(40).maxWidth(90).build(),
                ColumnConstraintsBuilder.create().minWidth(50).maxWidth(70).build()    
            );

            clockPane.getRowConstraints().setAll(
                    RowConstraintsBuilder.create().minHeight(10).build(),
                    RowConstraintsBuilder.create().minHeight(20).build(),
                    RowConstraintsBuilder.create().minHeight(67).build(),
                    RowConstraintsBuilder.create().minHeight(10).build()
            );

            clockPane.setHgap(0);
    //        clockPane.setGridLinesVisible(true);
    //        clockPane.setMaxHeight(50);

            hour_Label.setId("hour");  
            clockPane.setHalignment(hour_Label,HPos.RIGHT);
            clockPane.setConstraints(hour_Label, 1, 2, 1, 3);
            clockPane.getChildren().add(hour_Label);

            separator_Label.setText(":");
            separator_Label.setId("clock_separator");  
            clockPane.setHalignment(separator_Label,HPos.CENTER);
            clockPane.setConstraints(separator_Label, 2, 2, 1, 3);
            clockPane.getChildren().add(separator_Label);

            minute_Label.setId("minute");  
            clockPane.setHalignment(minute_Label,HPos.CENTER);
            clockPane.setValignment(minute_Label,VPos.CENTER);
            clockPane.setConstraints(minute_Label, 3, 2, 1, 3);
            clockPane.getChildren().add(minute_Label);

            second_Label.setId("second");  
            clockPane.setHalignment(second_Label,HPos.CENTER);
            clockPane.setValignment(second_Label,VPos.CENTER);
            clockPane.setConstraints(second_Label, 4, 2, 1, 3);
            clockPane.getChildren().add(second_Label);

            date_Label.setId("date");
            clockPane.setHalignment(date_Label,HPos.CENTER);
            clockPane.setConstraints(date_Label, 1, 3,5,1);
            clockPane.getChildren().add(date_Label);
            
            date_Label.setTranslateY(-6);

        }
        
        
        else
        {
            clockPane.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().minWidth(10).build(),
                ColumnConstraintsBuilder.create().minWidth(140).build(),
                ColumnConstraintsBuilder.create().minWidth(65).build(),
                ColumnConstraintsBuilder.create().minWidth(140).build(),
                ColumnConstraintsBuilder.create().minWidth(140).build()    
            );

            clockPane.getRowConstraints().setAll(
                    RowConstraintsBuilder.create().minHeight(10).build(),
                    RowConstraintsBuilder.create().minHeight(20).build(),
                    RowConstraintsBuilder.create().minHeight(167).build(),
                    RowConstraintsBuilder.create().minHeight(10).build()
            );

            clockPane.setHgap(0);
    //        clockPane.setGridLinesVisible(true);
    //        clockPane.setMaxHeight(50);

            hour_Label.setId("hour");  
            clockPane.setHalignment(hour_Label,HPos.RIGHT);
            clockPane.setConstraints(hour_Label, 1, 2, 1, 3);
            clockPane.getChildren().add(hour_Label);
            hour_Label.setTranslateY(-5);

            separator_Label.setText(":");
            separator_Label.setId("clock_separator");  
            clockPane.setHalignment(separator_Label,HPos.CENTER);
            clockPane.setConstraints(separator_Label, 2, 2, 1, 3);
            clockPane.getChildren().add(separator_Label);
            separator_Label.setTranslateY(-5);

            minute_Label.setId("minute");  
            clockPane.setHalignment(minute_Label,HPos.CENTER);
            clockPane.setValignment(minute_Label,VPos.CENTER);
            clockPane.setConstraints(minute_Label, 3, 2, 1, 3);
            clockPane.getChildren().add(minute_Label);
            minute_Label.setTranslateY(-5);

            second_Label.setId("second");  
            clockPane.setHalignment(second_Label,HPos.CENTER);
            clockPane.setValignment(second_Label,VPos.CENTER);
            clockPane.setConstraints(second_Label, 4, 2, 1, 3);
            clockPane.getChildren().add(second_Label);
            second_Label.setTranslateY(-5);

            date_Label.setId("date");
            clockPane.setHalignment(date_Label,HPos.CENTER);
            clockPane.setConstraints(date_Label, 1, 3,5,1);
            clockPane.getChildren().add(date_Label);
        }

        return clockPane;
    }
    
    //===MOON PANE==========================  
    public GridPane logopane() {
      
        GridPane Logopane = new GridPane();
        
        
        
            Logopane.getColumnConstraints().setAll(
                    ColumnConstraintsBuilder.create().minWidth(350).build(),
//                    ColumnConstraintsBuilder.create().minWidth(50).build(),
                    ColumnConstraintsBuilder.create().minWidth(160).build()     
            );
            Logopane.setHgap(30);
            Logopane.setMaxHeight(50);
//           Moonpane.setGridLinesVisible(true);

            ImageView Logo_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/Logo/MIA_white.png")));      
            Logo_img.setFitHeight(210);
            Logo_img.setPreserveRatio(true);
            Logo_img.setSmooth(true);
            
            
            Logo_Image_Label.setGraphic(Logo_img);
            Logopane.setConstraints(Logo_Image_Label, 1, 0);
            Logopane.getChildren().add(Logo_Image_Label); 
            
 
        return Logopane;
    }
    
    
    
    
 }



                        
//                        
//                       
//
//                        Locale locale = Locale.getDefault();
//        TimeZone localTimeZone = TimeZone.getDefault(); 
//        //TimeZone localTimeZone = TimeZone.getTimeZone(timeZone_ID);
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
//        dateFormat.setTimeZone(localTimeZone);
//        Date rightNow = new Date();
//        System.out.println(locale.toString() + ": " + dateFormat.format(rightNow));
//                        
//                        
// 

//ProcessBuilder processBuilder = 
//                            new ProcessBuilder("bash", "-c", "aplay /home/pi/javafx/examples/PrayerTime/src/Audio/athan1.wav");
//                            try {
//                                Process process = processBuilder.start();
//                                InputStream stderr = process.getErrorStream();
//                                InputStreamReader isr = new InputStreamReader(stderr);
//                                BufferedReader br = new BufferedReader(isr);
//                                String line = null;
//                                while ((line = br.readLine()) != null) {
//                                    System.out.println(line);
//                                }
//                            } catch (IOException ex) {
//                                logger.warn("Unexpected error", e);
//                            }
