import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {AuthProvider} from './AuthContext';
import './i18n';
import i18n from "./i18n";
import {I18nextProvider} from "react-i18next";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <AuthProvider>
        <I18nextProvider i18n={i18n}>
            <App/>
        </I18nextProvider>
    </AuthProvider>
);

reportWebVitals();
