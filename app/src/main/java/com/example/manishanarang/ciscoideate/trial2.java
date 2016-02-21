package com.example.manishanarang.ciscoideate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class trial2 extends AppCompatActivity {


    // Progress Dialog
    //private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    int success;
    JSONObject json;
    //ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url = "http://localhost/ideate/try.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    //private static final String TAG_PRODUCTS = "products";
    //private static final String TAG_PID = "pid";
    //private static final String TAG_NAME = "name";

    // products JSONArray
    JSONArray products = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial2);

        // Hashmap for ListView
        // productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadAllProducts().execute();



    }

    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* pDialog = new ProgressDialog(AllProductsActivity.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
            */
            Toast.makeText(getApplicationContext(), "inside preexecute", Toast.LENGTH_LONG).show();
        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {
            // Building Parameters
        // List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            try {
                json = jParser.makeHttpRequest(url, "POST");
            }
            catch (Exception e) {
                e.printStackTrace();
            }            // Check your log cat for JSON reponse
          //  Log.d("All Products: ", json.toString());

           /* try {
                // Checking for SUCCESS TAG
                //success = json;

            }catch (JSONException e) {
                e.printStackTrace();
            }
*/

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {

            try {
                // Checking for SUCCESS TAG
               // int success = json.getInt(TAG_SUCCESS);
                int success=1;
                if (success == 1) {

                    // products found
                    // Getting Array of Products
                    //products = json.getJSONArray(TAG_PRODUCTS);

                    /*// looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                    */
                    Toast.makeText(getBaseContext(), json.toString(), Toast.LENGTH_LONG).show();

                } else {
                    // no products found
                    // Launch Add New product Activity
                   /* Intent i = new Intent(getApplicationContext(),
                            NewProductActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }*/
                    Toast.makeText(getApplicationContext(), "else vala block", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
