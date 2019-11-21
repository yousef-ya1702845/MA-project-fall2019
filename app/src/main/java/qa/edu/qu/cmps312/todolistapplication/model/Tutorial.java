package qa.edu.qu.cmps312.todolistapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tutorial implements Parcelable {
    private String subject;
    private String name;
    private String phone;
    private String info;


    public Tutorial() {

    }

    public Tutorial(String subject, String name, String phone, String info) {
        this.subject = subject;
        this.name = name;
        this.phone = phone;
        this.info = info;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.subject);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.info);

    }

    protected Tutorial(Parcel in) {
        this.subject = in.readString();
        this.name = in.readString();
        this.phone = in.readString();
        this.info = in.readString();

    }

    public static final Parcelable.Creator<Tutorial> CREATOR = new Parcelable.Creator<Tutorial>() {
        @Override
        public Tutorial createFromParcel(Parcel source) {
            return new Tutorial(source);
        }

        @Override
        public Tutorial[] newArray(int size) {
            return new Tutorial[size];
        }
    };

    @Override
    public String toString() {
        return "Tutorial{" +
                "subject='" + subject + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", info='" + info + '\'' +

                '}';
    }
}
