package com.kbear.noknok.service.response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kbear.noknok.dtos.Account;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.dtos.Message;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

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

    public interface IMessageResponseParser {
        public Message parse(JSONObject response);
    }

    public interface IUsernamesResponseParser {
        public List<String> parse(JSONObject response);
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
        public Account parse(JSONObject response) {
            try {
                return new Gson().fromJson(response.getString("result"), Account.class);
            } catch (JSONException ex) {
                return null;
            }
        }
    };

    public IMessageResponseParser messageResponseParser = new IMessageResponseParser() {
        @Override
        public Message parse(JSONObject response) {
            return new Gson().fromJson(response.toString(), Message.class);
        }
    };

    public IUsernamesResponseParser usernamesResponseParser = new IUsernamesResponseParser() {
        @Override
        public List<String> parse(JSONObject response) {
            try {
                String usernames = response.getString("usernames");
                Type listType = new TypeToken<List<String>>() {}.getType();
                return new Gson().fromJson(usernames, listType);
            } catch (JSONException e) {
                e.printStackTrace();
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
