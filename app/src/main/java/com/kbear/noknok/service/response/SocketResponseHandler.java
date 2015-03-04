package com.kbear.noknok.service.response;

import com.google.gson.Gson;
import com.kbear.noknok.dtos.Account;
import com.kbear.noknok.dtos.CustomError;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by allen on 3/3/15.
 */
public class SocketResponseHandler {

    public interface IBooleanResponseParser {
        public boolean parse(JSONObject response);
    }

    public interface IAccountResponseParser {
        public Account parse(JSONObject response);
    }

    public interface ICustomErrorResponseParser {
        public CustomError parse(JSONObject error);
    }

    public IBooleanResponseParser booleanResponseParser = new IBooleanResponseParser() {
        @Override
        public boolean parse(JSONObject response) {
            try {
                JSONObject result = response.getJSONObject("result");
                return result.getInt("success") == 1;
            } catch (JSONException e) {
                return false;
            }
        }
    };

    public IAccountResponseParser accountResponseParser = new IAccountResponseParser() {
        @Override
        public Account parse(JSONObject result) {
            try {
                return new Gson().fromJson(result.getString("result"), Account.class);
            } catch (JSONException ex) {
                return null;
            }
        }
    };

    public ICustomErrorResponseParser customErrorParser = new ICustomErrorResponseParser() {
        @Override
        public CustomError parse(JSONObject error) {
            try {
                return new Gson().fromJson(error.getString("error"), CustomError.class);
            } catch (JSONException ex) {
                return null;
            }
        }
    };
}
