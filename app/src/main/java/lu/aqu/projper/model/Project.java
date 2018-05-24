package lu.aqu.projper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("features")
    private List<String> features;
    @SerializedName("tags")
    private List<String> tags;

    protected Project(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        features = in.createStringArrayList();
        tags = in.createStringArrayList();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeStringList(features);
        parcel.writeStringList(tags);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public List<String> getTags() {
        return tags;
    }
}
