package org.xo.demo.tool.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import org.xo.demo.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReflectActivity extends Activity {

    private EditText mEditClassName, mEditFieldName, mEditMethodName;
    private Button mBtnQuery, mBtnClear;
    private TextView mTextQueryResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_reflect_activity);

        inflateViews();
    }

    private void inflateViews() {
        mEditClassName = (EditText) findViewById(R.id.edit_class_name);
        mEditFieldName = (EditText) findViewById(R.id.edit_field_name);
        mEditMethodName = (EditText) findViewById(R.id.edit_method_name);

        mTextQueryResult = (TextView) findViewById(R.id.text_query_result);

        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnQuery.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                QueryParam param = QueryParam.create(
                        mEditClassName.getText().toString(),
                        mEditFieldName.getText().toString(),
                        mEditMethodName.getText().toString()
                        );

                queryByReflect(param);
            }
        });

        mBtnClear = (Button) findViewById(R.id.btn_clear);
        mBtnClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                clearResult();
            }
        });
    }

    private void queryByReflect(QueryParam param) {
        if(param.isInvalid()) {
            return;
        }

        try {
            Class<?> klass = Class.forName(param.mClassName);
            mEditClassName.setBackgroundColor(Color.GREEN);

            Field[] fields  = klass.getDeclaredFields();
            Method[] methods = klass.getDeclaredMethods();
            mEditFieldName.setBackgroundColor(
                    containsInMember(fields, param.mFieldName) ? Color.GREEN : Color.RED);
            mEditMethodName.setBackgroundColor(
                    containsInMember(methods, param.mMethodName) ? Color.GREEN : Color.RED);

            StringBuilder sb = new StringBuilder();
            String tmp;
            for(int i = 0; i < fields.length; i++) {
                tmp = "Field[" + i + "] : " + fields[i].getName();
                Log.i("ReflectActivity", tmp);
                sb.append(tmp).append("\n");
            }

            for(int j = 0; j < methods.length; j++) {
                tmp = "Method[" + j + "] : " + methods[j].getName();
                Log.i("ReflectActivity", tmp);
                sb.append(tmp).append("\n");
            }

            mTextQueryResult.setText(sb);

        } catch (ClassNotFoundException e) {
            mEditClassName.setBackgroundColor(Color.RED);
        }

    }

    private boolean containsInMember(Member[] classMember, String targetName) {
        for(int i = 0; i < classMember.length; i++) {
            if(classMember[i].getName().equals(targetName)) {
                return true;
            }
        }

        return false;
    }

    private void clearResult() {
        mEditClassName.setBackgroundResource(0);
        mEditFieldName.setBackgroundResource(0);
        mEditMethodName.setBackgroundResource(0);
        mTextQueryResult.setText("Query Result");
    }
}

class QueryParam {
    String mClassName;
    String mMethodName;
    String mFieldName;

    static QueryParam QUERY_PARAM = new QueryParam();

    static QueryParam create(String className, String fieldName, String methodName) {
        QUERY_PARAM.mClassName = className;
        QUERY_PARAM.mMethodName = methodName;
        QUERY_PARAM.mFieldName = fieldName;
        return QUERY_PARAM;
    }

    boolean isInvalid() {
        return TextUtils.isEmpty(mClassName) &&
               TextUtils.isEmpty(mMethodName) &&
               TextUtils.isEmpty(mFieldName);
    }
}