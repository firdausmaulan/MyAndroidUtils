package com.myandroid.utils.collections;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextWatcherUtil {

    TextChangeListener textChangeListener;

    public TextWatcherUtil setListener(TextChangeListener callback) {
        this.textChangeListener = callback;
        return this;
    }

    public TextWatcherUtil setEditText(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChangeListener.whenTextChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return this;
    }
}

