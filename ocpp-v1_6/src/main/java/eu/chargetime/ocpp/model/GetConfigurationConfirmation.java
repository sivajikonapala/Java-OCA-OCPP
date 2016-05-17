package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

/**
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
public class GetConfigurationConfirmation implements Confirmation {
    private KeyValueType[] configurationKey;
    private String[] unknownKey;

    public KeyValueType[] getConfigurationKey() {
        return configurationKey;
    }

    public void setConfigurationKey(KeyValueType[] configurationKey) {
        this.configurationKey = configurationKey;
    }

    public String[] getUnknownKey() {
        return unknownKey;
    }

    public void setUnknownKey(String[] unknownKey) throws PropertyConstraintException {
        if (!isValidUnknownKey(unknownKey))
            throw new PropertyConstraintException("unknownKey", unknownKey);

        this.unknownKey = unknownKey;
    }

    private boolean isValidUnknownKey(String[] unknownKeys) {
        boolean output = true;
        for(String key: unknownKeys) {
            if ((output = ModelUtil.validate(key, 50)) == false) break;
        }
        return output;
    }

    private boolean validateConfigurationKeys() {
        boolean output = true;
        if (configurationKey != null && configurationKey.length > 0) {
            for (KeyValueType key : configurationKey) {
                if ((output = key.validate()) == false)
                    break;
            }
        }
        return output;
    }

    @Override
    public boolean validate() {
        boolean output = true;
        output &= validateConfigurationKeys();
        return output;
    }
}
