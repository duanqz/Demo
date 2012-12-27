package org.xo.demo.helloworld.helloaccount;

import org.xo.demo.R;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Hello world of Account Manager.
 */
public class HelloAccountActivity extends AccountAuthenticatorActivity implements OnClickListener {

    private static final String TAG = "HelloAccountActivity";

    private AccountManager mAccountManager;

    // UI
    private TextView mAccountListTextView;
    private EditText mAccountNameTextView,
                     mAccountTypeTextView,
                     mAccountPassTextView;
    private Button mAddAccountButton,
                   mRemAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_account_activity);

        initResource();
    }

    @Override
    protected void onResume() {
        super.onResume();

        listAllAccounts();
    }

    @Override
    public void onClick(View v) {
        if(v == mAddAccountButton) {
            onAddAccount();
        } else if(v == mRemAccountButton) {
            onRemoveAccount();
        }
    }

    private void initResource() {
        mAccountListTextView = (TextView) findViewById(R.id.account_list);
        mAccountNameTextView = (EditText) findViewById(R.id.account_name);
        mAccountTypeTextView = (EditText) findViewById(R.id.account_type);
        mAccountPassTextView = (EditText) findViewById(R.id.account_password);
        mAddAccountButton = (Button) findViewById(R.id.add_account);
        mAddAccountButton.setOnClickListener(this);
        mRemAccountButton = (Button) findViewById(R.id.rem_account);
        mRemAccountButton.setOnClickListener(this);
    }

    private void listAllAccounts() {
        mAccountManager = AccountManager.get(this);
        StringBuilder sb = new StringBuilder();
        for(Account account : mAccountManager.getAccounts()) {
            Log.i(TAG, "Account" + account);
            sb.append(account)
            .append("\n");
        }
        mAccountListTextView.setText(sb);
    }

    private void onAddAccount() {
        // Get account name and type from UI input
        String accountName = mAccountNameTextView.getText().toString();
        String accountType = mAccountTypeTextView.getText().toString();

        if(TextUtils.isEmpty(accountName) || TextUtils.isEmpty(accountType)) {
            return;
        }

        Account account = new Account(accountName, accountType);
        String password = mAccountPassTextView.getText().toString();

        // Add account
        boolean addSuccess = false;
        try {
            Bundle userdata = new Bundle();
            addSuccess = mAccountManager.addAccountExplicitly(account, password, userdata);
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.invalid_account_type_toast), Toast.LENGTH_SHORT).show();
            return;
        }

        if(addSuccess) {
            Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            // Set authenticator result
            setAccountAuthenticatorResult(result);

            // List all accounts
            listAllAccounts();

        } else {
            Toast.makeText(this, getString(R.string.account_already_exists), Toast.LENGTH_SHORT).show();

        }

    }

    private void onRemoveAccount() {
        // Get account name and type from UI input
        String accountName = mAccountNameTextView.getText().toString();
        String accountType = mAccountTypeTextView.getText().toString();

        if(TextUtils.isEmpty(accountName) || TextUtils.isEmpty(accountType)) {
            return;
        }

        Account account = new Account(accountName, accountType);
        mAccountManager.removeAccount(account, null, null);
        listAllAccounts();
    }

}
