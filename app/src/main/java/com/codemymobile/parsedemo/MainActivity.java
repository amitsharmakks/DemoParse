package com.codemymobile.parsedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseRole;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // addSchool();

     //   addComments();

         // getIndividualSchool();

          // addNewUser();
      signInAccount();

       // addStudent();
    //    getListOfSchool();

   //  logOutUser();

     //  addRole();
   //     addUserIntoRole();

       // addChildRole();
//getListOfStudentOneToMany();

    //    addFile();
     //   getFiles();
  //  getListOfSchool();

//        ParseObject parse=new ParseObject("Student");
//        ParseRelation<ParseUser> relation = parse.getRelation("Comments");
//        relation.add(ParseUser.getCurrentUser());
////        relation.add(parseUser2);
//        parse.saveInBackground();
//


        ParseObject parse=new ParseObject("Comments");
        parse.put("name", ParseUser.getCurrentUser().getUsername());
        parse.put("message", "okay dude Working s");

//        ParseRelation<ParseUser> relation = parse.getRelation("Student");
//        relation.add(ParseUser.getCurrentUser());
////        relation.add(parseUser2);
//        parse.saveInBackground();



        //ParseObject parseStudent=new ParseObject("Student");


      //  parse.put("createdBy_", ParseObject.createWithoutData("Student","J4NUKNcpTH"));
//        parse.put("createdBy_", ParseObject.createWithoutData("Student",parse.getObjectId()));

    //    parse.saveInBackground();
        // ParseUser.createWithoutData("Comments",parse.getObjectId());

//pointers();

//        relations();
     //   relations();
getTeachersStudent();
    //   getComments();
    }

    private void getTeachersStudent() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Teacher");

    // Retrieve the most recent ones
        query.whereEqualTo("objectId","SPIzJmhpsr");
//        query.orderByDescending("createdAt");
//        query.include("Students");
//        String[] names = {"VEm3z2KHKT ", "J4NUKNcpTH"};
//        query.whereContainedIn("objectId", Arrays.asList(names));

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> teacherObjs, ParseException e) {

                ParseQuery<ParseObject> queryParse=ParseQuery.getQuery("Student");
                        String[] names = {"VEm3z2KHKT ", "J4NUKNcpTH"};
                queryParse.whereContainedIn("objectId", Arrays.asList(names));
                queryParse.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        //String [] arr = (String[]) objects.toArray();

//                        String[] arr = new String[objects.size()];
//                        arr = objects.toArray(arr);

//                        String[] charSequence = new String[objects.size()];
//                        for (int ik = 0; ik<objects.size(); ik++) {
//                            charSequence[ik] = String.valueOf(objects.get(ik));
//                        }

                        ParseObject parseObj=new ParseObject("Teacher");
                        parseObj.addAll("studentsArray",objects);

                        parseObj.put("name","teacher 2");
                        parseObj.saveInBackground();
                    }
                });

            }
        });

    }
    private void getComments() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Comments");

    // Retrieve the most recent ones
        query.orderByDescending("createdAt");
        query.include("createdBy_");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                // commentList now contains the last ten comments, and the "post"
                // field has been populated. For example:
                for (ParseObject comment : commentList) {
                    // This does not require a network access.
                    ParseObject post = comment.getParseObject("post");
//                    Log.d("post", "retrieved a related post" + comment.get("info"));
                    Log.d("post", "retrieved a related post" + comment.get("createdBy_"));

                //    ParseObject obj=comment.getParseObject("createdBy_");
              //      Log.e(TAG, "done:##### "+obj.getString("class") );

                }
            }
        });

    }

    private void relations() {
        ParseObject parse=new ParseObject("Teacher");
        parse.put("name", "Teacher 2");
        parse.put("age", "28");
        parse.put("subject", "Hindi");

        ParseRelation<ParseObject> relations =parse.getRelation("Students");
        relations.add(ParseUser.getCurrentUser());

        //ParseRelation<ParseUser> relation = parse.getRelation("Students");
        //relation.add(ParseUser.getCurrentUser());


//        relation.add(parseUser2);
        parse.saveInBackground();
    }


    private void pointers()
    {
        ParseObject parse=new ParseObject("Comments");
        parse.put("name", ParseUser.getCurrentUser().getUsername());
        parse.put("message", "okay dude Working by DPS");
        parse.put("createdBy_", ParseObject.createWithoutData("Student","J4NUKNcpTH"));
//        parse.put("createdBy_", ParseObject.createWithoutData("Student",parse.getObjectId()));

        parse.saveInBackground();



    }

//        ParseUser currentUser = ParseUser.getCurrentUser();

    public void add(ParseUser user) {

        ParseRelation<ParseUser> relation = user.getRelation("likes");
        relation.add(user);
        user.saveInBackground();
    }

    private void addComments() {
        ParseObject schoolData = new ParseObject("Comments");
        schoolData.put("name", "Amit");
        schoolData.put("message", "okay dude ");
      schoolData.saveInBackground();
    }

    private void getFiles() {
        ParseObject jobApplication = new ParseObject("Student");

        ParseFile applicantResume = (ParseFile)jobApplication.get("info");
        applicantResume.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    Log.e(TAG, "done: "+"Working fine file"+data.length );
                    // data has the bytes for the resume
                } else {
                    e.printStackTrace();
                    // something went wrong
                }
            }
        });
    }

    private void addFile() {

//        final File file = new File(Environment.getExternalStorageDirectory()
//                .getAbsolutePath(), "abc.txt.txt");
//        Log.e(TAG, "addFile: "+file.exists()
//        );

        byte[] data = "Working at Parse is great!".getBytes();
        ParseFile file = new ParseFile("resume.txt", data);

        ParseObject jobApplication = new ParseObject("Student");
        jobApplication.put("name", "Joe Smith");
        jobApplication.put("info", file);
        jobApplication.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private void getListOfStudentOneToMany() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");

// Retrieve the most recent ones
//        query.orderByDescending("createdAt");
        query.whereEqualTo("address","Kota");

// Only retrieve the last ten
        //  query.setLimit(10);

        //   query.include("post");
// Include the post from local DB
        // query.fromLocalDatastore();

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                // commentList now contains the last ten comments, and the "post"
                // field has been populated. For example:
                for (ParseObject comment : commentList) {
                    // This does not require a network access.
                    ParseObject post = comment.getParseObject("post");
                    Log.d("post", "retrieved a related post" + comment.get("schoolName"));


                }
            }
        });

    }
    private void getListOfStudentManyToMany() {



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");

// Retrieve the most recent ones
//        query.orderByDescending("createdAt");
        query.whereEqualTo("address","Kota");
// Only retrieve the last ten
        //  query.setLimit(10);

        //   query.include("post");
// Include the post from local DB
        // query.fromLocalDatastore();

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                // commentList now contains the last ten comments, and the "post"
                // field has been populated. For example:
                for (ParseObject comment : commentList) {
                    // This does not require a network access.
                    ParseObject post = comment.getParseObject("post");
                    Log.d("post", "retrieved a related post" + comment.get("schoolName"));


                }
            }
        });

    }

    private void addStudent(){


//        final File file = new File(Environment.getExternalStorageDirectory()
//                .getAbsolutePath(), "customized-capability.xml");
//
//        Log.e(TAG, "addfile: "+file.exists());

        ParseObject schoolData = new ParseObject("Student");
        schoolData.put("name", "Stdnt 5");
        schoolData.put("schoolName", "MGPS ");
        schoolData.put("address", "Kota");
        schoolData.put("class", "1");
//        schoolData.put("appfile", file);


        schoolData.saveInBackground(); // for cloud saving direct

    }

    private void addChildRole() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.e(TAG, "addUserIntoRole: "+currentUser.getUsername());

        ParseACL roleACL = new ParseACL();
        roleACL.setPublicReadAccess(true);
        roleACL.setPublicWriteAccess(true);
        ParseRole administrators =new ParseRole("Administrators",roleACL);//= /* Your "Administrators" role */;
        ParseRole moderators= new ParseRole("Moderators",roleACL);//= /* Your "Moderators" role */;
        moderators.getRoles().add(administrators);
        moderators.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                e.printStackTrace();
            }
        });





    }



    private void addUserIntoRole() {

        ParseACL roleACL = new ParseACL();
        roleACL.setPublicReadAccess(true);
        roleACL.setPublicWriteAccess(true);
        ParseRole role = new ParseRole("Admin", roleACL);

//        for (ParseUser user : usersToAddToRole) {
//            role.getUsers().add(user)
//        }
//        for (ParseRole childRole : rolesToAddToRole) {
//            role.getRoles().add(childRole);
//        }
        Log.e(TAG, "addUserIntoRole: "+role.getName());


        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.e(TAG, "addUserIntoRole: "+currentUser.getUsername());
        role.getUsers().add(currentUser);

        role.saveInBackground();
    }

    private void addRole() {
        ParseACL roleACL = new ParseACL();
        roleACL.setPublicReadAccess(true);
        roleACL.setPublicWriteAccess(true);

     //  ParseRole roleChild  = new ParseRole("Admin", roleACL);
        ParseRole roleChild = new ParseRole("Requestors", roleACL);
//        ParseRole roleChild = new ParseRole("Moderators", roleACL);
      //  role.saveInBackground();
        Log.e(TAG, "addUserIntoRole: "+roleChild.getName());

        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.e(TAG, "addUserIntoRole: "+currentUser.getUsername());
        roleChild.getUsers().add(currentUser);
//        roleChild.getRoles().add(roleChild);
        roleChild.saveInBackground();

//        role.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                e.printStackTrace();
//            }
//        });
    }

    private void logOutUser() {

        ParseUser.logOut();

    }

    private void signInAccount() {
//       ParseUser.logInInBackground("Amit", "Amit123", new LogInCallback() {
       //ParseUser.logInInBackground("An", "An123", new LogInCallback() {
       ParseUser.logInInBackground("Aks", "Aks123", new LogInCallback() {
    //   ParseUser.logInInBackground("test1234", "test1234", new LogInCallback() {
//        ParseUser.logInInBackground("test123", "test123", new LogInCallback() {
        //ParseUser.logInInBackground("Sumit", "Smit123", new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.

                    Log.e(TAG, "done: " + "SignIn SuccessFully "+user.getSessionToken());

                    getListOfSchool();
                  //  addUserIntoRole();
                //    verifyingToken(user.getSessionToken());

                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    Log.e(TAG, "done: " + "Unable to SignIn !!");

                }
            }
        });
    }

    private void verifyingToken(String token) {
        ParseUser.becomeInBackground(token, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // The current user is now set to user.
                    Log.e(TAG, "done: "+"Valid User" );

                    getListOfSchool();
//addSchool();
                  //  updateUser();
                } else {
                    // The token could not be validated.
                    Log.e(TAG, "done: "+"not a Valid User" );

                }
            }
        });
    }

    private void updateUser() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("School");

// Retrieve the object by id
        query.getInBackground("4wofHwxrXo", new GetCallback<ParseObject>() {
            public void done(ParseObject schoolData, ParseException e) {
                if (e == null) {
                    // Now let's update it with some new data. In this case, only cheatMode and score
                    // will get sent to the Parse Cloud. playerName hasn't changed.
                    schoolData.put("schoolName", "MGPS Vdh ngr");
                    ParseGeoPoint points = new ParseGeoPoint(40.0, -30.0);

                    schoolData.put("schoolAddress", points);

                    schoolData.saveInBackground();
                    Log.e(TAG, "done: "+ "Updated");
                    getListOfSchool();
                }
                else {
                    e.printStackTrace();

                    Log.e(TAG, "done: "+"not updated"+e.getMessage());
                }
            }
        });



    }

    private void addNewUser() {
        ParseUser user = new ParseUser();
        user.setUsername("test1234");
        user.setPassword("test1234");
        user.setEmail("test14@gmail.com");

// other fields can be set just like with ParseObject
        user.put("phone", "8233247827");

        //ParseACL acl=new ParseACL();
        //acl.setRoleReadAccess("Moderators",true);
        //user.setACL(acl);

//        ParseACL roleACL = new ParseACL();
//        roleACL.setPublicReadAccess(true);
//        ParseRole role = new ParseRole("Administrator", roleACL);
//        role.saveInBackground();
//
//        role.getUsers().add(user);
//        role.saveInBackground();



        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.e(TAG, "done: " + "New User Created");
                    // Hooray! Let them use the app now.
                    addSchool();
                } else {
                    e.printStackTrace();
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG, "done: " + "Unable to Create New User");

                }
            }
        });

    }

    private void getListOfSchool() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("School");

// Retrieve the most recent ones
        query.orderByDescending("createdAt");

// Only retrieve the last ten
        //  query.setLimit(10);

        //   query.include("post");
// Include the post from local DB
        // query.fromLocalDatastore();

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                // commentList now contains the last ten comments, and the "post"
                // field has been populated. For example:
                for (ParseObject comment : commentList) {
                    // This does not require a network access.
                    ParseObject post = comment.getParseObject("post");
//                    Log.d("post", "retrieved a related post" + comment.get("info"));
                    Log.d("post", "retrieved a related post" + comment.get("schoolName"));


                }
            }
        });

    }

    private void getIndividualSchool() {

        ParseUser user = null;
        try {
            user = ParseUser.logIn("Amit", "Amit123");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.saveInBackground(); // This succeeds, since the user was authenticated on the device



        ParseQuery<ParseObject> query = ParseQuery.getQuery("School");
        //  query.fromLocalDatastore();

      //  query.getInBackground(user.getObjectId(), new GetCallback<ParseObject>() {
        query.getInBackground("8iAM3SM0If", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    Log.e(TAG, "done: " + object.get("schoolName"));
                    // object will be your game score
                } else {
                    e.printStackTrace();

                    //   Log.e(TAG, "Not done: " + object.get("schoolName"));

                    // something went wrong
                }
            }
        });
    }


    private void addSchool() {

        ParseACL groupACL = new ParseACL();


        ParseObject schoolData = new ParseObject("School");
        schoolData.put("schoolName", "SN Public School Jaipur");
        ParseGeoPoint points = new ParseGeoPoint(40.0, -30.0);

        groupACL.setPublicReadAccess(true);
        groupACL.setPublicWriteAccess(true);
//        groupACL.setRoleWriteAccess("Moderators",true);
        groupACL.setRoleReadAccess("Administrator",true);

        // schoolData.pinInBackground();



        schoolData.put("schoolAddress", points);
        schoolData.setACL(groupACL);

        schoolData.saveInBackground(); // for cloud saving direct

        getListOfSchool();
    }
}
