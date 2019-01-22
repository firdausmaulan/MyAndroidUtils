package com.myandroid.utils.collections

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage

class SMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        try {
            val bundle = intent.extras
            if (bundle != null) {
                val pdu_Objects = bundle.get("pdus") as Array<ByteArray>
                if (pdu_Objects != null) {
                    for (aObject in pdu_Objects) {
                        val currentSMS = getIncomingMessage(aObject, bundle)
                        val sender = currentSMS.displayOriginatingAddress
                        val message = currentSMS.displayMessageBody
                        LogUtil.d(sender)
                        LogUtil.d(message)
                        val i = Intent(Constants.TAG_SMS_RECEIVER)
                        i.putExtra(Constants.OTP_PHONE_NUMBER, sender)
                        i.putExtra(Constants.OTP_MESSAGE, message)
                        context.sendBroadcast(i)
                    }
                    this.abortBroadcast()
                    // End of loop
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getIncomingMessage(aObject: ByteArray, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bundle.getString("format")
            currentSMS = SmsMessage.createFromPdu(aObject, format)
        } else {
            currentSMS = SmsMessage.createFromPdu(aObject)
        }
        return currentSMS
    }
}