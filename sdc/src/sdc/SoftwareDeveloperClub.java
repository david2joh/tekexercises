package sdc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SoftwareDeveloperClub {
    private List<ClubMember> members;

    public SoftwareDeveloperClub(String fname) throws FileNotFoundException {
        File fp = new File(fname);
        FileReader fr = new FileReader(fp);
        members = new ArrayList<ClubMember>();
        try(BufferedReader br = new BufferedReader(fr)) {
        String line;
        br.readLine();  // thow away the header
        while ((line = br.readLine()) != null) {
            System.out.println("line: "+ line);
          String [] memberRec = line.split("\\*\\*", 5);
           ClubMember cm = new ClubMember(memberRec[0],memberRec[1],memberRec[2],memberRec[3]);
           members.add(cm);
        }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        members.forEach(cm->sb.append(cm.toString()));
        return sb.toString();
    }

    public String streamToString() {
            return   members.stream().map(ClubMember::toString).collect(Collectors.joining());
}

    public void add(ClubMember c1) {
        members.add(c1);
    }

    public int size() {
        return members.size();
    }

    public ClubMember get(int i) {
        return members.get(i);
    }

    public void remove(int i) {
        members.remove(i);
    }
}
