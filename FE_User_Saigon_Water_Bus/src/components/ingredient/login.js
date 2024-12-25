import React, { useEffect, useState } from 'react';
import LoginService from '../../services/loginServices';
import { useAuth } from '../../AuthContext';
import axios from 'axios';
import { useTranslation } from 'react-i18next';
import '../../i18n';
import LanguageSwitcher from "../DefaulLayout/LanguageSwitcher";
const apiUrl = process.env.REACT_APP_API_URL;

function Login() {
    const { t } = useTranslation();
    const { isLoggedIn, login } = useAuth();
    const [credentials, setCredentials] = useState({
        username: '',
        password: ''
    });
    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [errorLogin, setErrorLogin] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    const toggleShowPassword = () => {
        setShowPassword(!showPassword);
    };

    useEffect(() => {
        const handleGoogleRedirect = async () => {
            const urlParams = new URLSearchParams(window.location.search);
            const code = urlParams.get('code');

            if (code) {
                try {
                    await LoginService.LoginGoogle(code);
                    login();
                    setShowSuccessMessage(true);
                    setTimeout(() => {
                        setShowSuccessMessage(false);
                        window.location.href = '/';
                    }, 2000);
                } catch (error) {
                    console.error('Error occurred while processing Google redirect:', error);
                }
            }
        };

        handleGoogleRedirect();
    }, []); // Chỉ chạy một lần khi component được mount

    useEffect(() => {
        if (isLoggedIn) {
            setShowSuccessMessage(true);
            setTimeout(() => {
                window.location.href = '/';
            }, 2000);
        }
    }, [isLoggedIn]);

    const handleUsernameChange = (e) => {
        const { value } = e.target;
        setCredentials(prevState => ({
            ...prevState,
            username: value
        }));
    };

    const handlePasswordChange = (e) => {
        const { value } = e.target;
        setCredentials(prevState => ({
            ...prevState,
            password: value
        }));
    };

    const handleLogin = async (e) => {
        e.preventDefault(); // Ngăn chặn hành động mặc định của sự kiện
        try {
            // Gọi hàm login với đối tượng chứa thông tin đăng nhập
            await LoginService.LoginProcess(credentials.username, credentials.password);
            login();
        } catch (error) {
            setShowSuccessMessage(true);
            setTimeout(() => {
                setShowSuccessMessage(false);
            }, 2000);
            setErrorLogin('sai');
            console.error('Login failed:', error);
        }
    };

    const handleLoginWithGoogle = async () => {
        try {
            const response = await axios.get(`${apiUrl}/login/google`);
            if (response.status === 200 && response.data) {
                const authorizationUri = response.data;
                console.log("Redirecting to: ", authorizationUri);
                window.location.href = authorizationUri;
            } else {
                console.error('API did not return expected data:', response);
            }
        } catch (error) {
            console.error('Error occurred while logging in with Google:', error);
        }
    };

    return (
        <div className="LoginPage">

            {showSuccessMessage && (
                <div
                    className="fixed top-0 left-0 right-0 bottom-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                    <div
                        className="bg-white p-8 rounded-lg shadow-lg transform transition-all duration-300 ease-in-out">
                        <div className="flex items-center mb-4">
                            <svg className="animate-spin h-5 w-5 mr-3 text-purple-700"
                                 xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor"
                                        strokeWidth="4"></circle>
                                <path className="opacity-75" fill="currentColor"
                                      d="M4 12a8 8 0 018-8V0c4.418 0 8 3.582 8 8s-3.582 8-8 8v-4H4zm2 5v2a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 00-1-1H7a1 1 0 00-1 1zm2-9a1 1 0 011-1h2a1 1 0 011 1v6a1 1 0 01-1 1h-2a1 1 0 01-1-1V8z"></path>
                            </svg>
                            <span className="text-lg font-semibold text-purple-700">{t('login.loggingIn')}</span>
                        </div>
                    </div>
                </div>
            )}
                                                <div className="justify-end flex" ><LanguageSwitcher /></div>

            <div className="flex flex-wrap min-h-screen w-full content-center justify-center py-10 px-4">

                <div className="flex shadow-md w-full max-w-4xl">
                    <div
                        className="flex flex-wrap content-center justify-center rounded-l-md bg-white w-full lg:w-1/2 p-4">
                        <div className="w-full max-w-xs">
                            <h1 className="text-xl text-center font-semibold mb-4">
                                {t('login.welcome')}
                            </h1>
                            <small className=" text-center text-blue-600 font-bold text-base block mb-4">{t('login.login')}</small>
                            <div className="text-center mt-2">
                                {errorLogin &&
                                    <span className="font-bold text-xl text-red-500">{t('login.errorLogin')}</span>}
                            </div>
                            <form>
                                <div className="mb-3">
                                    <label htmlFor="username" className="block text-xs font-semibold mb-2">{t('login.username')}</label>
                                    <input
                                        id="username"
                                        type="text"
                                        placeholder={t('login.username')}
                                        className="block w-full rounded-md border border-gray-300 focus:border-purple-700 focus:outline-none focus:ring-1 focus:ring-purple-700 py-1 px-2 text-gray-500"
                                        value={credentials.username}
                                        onChange={handleUsernameChange}
                                    />
                                </div>
                                <div className="mb-3 relative">
                                    <label htmlFor="password" className="block text-xs font-semibold mb-2">{t('login.password')}</label>
                                    <input
                                        id="password"
                                        type={showPassword?"text":"password"}
                                        placeholder={t('login.password')}
                                        className="block w-full rounded-md border border-gray-300 focus:border-purple-700 focus:outline-none focus:ring-1 focus:ring-purple-700 py-1 px-2 text-gray-500"
                                        value={credentials.password}
                                        onChange={handlePasswordChange}
                                    />
                                    <button
                                        type="button"
                                        onClick={toggleShowPassword}
                                        className="absolute inset-y-0 right-0 top-5 flex items-center pr-3"
                                        >
                                        {showPassword ? <img src='/img/icon/hide-password.png' alt='' className='h-5'/> : <img src='/img/icon/show-password.png' alt='' className='h-5'/>}
                                    </button>

                                </div>
                                <div className="mb-3 flex items-center">
                                    <input
                                        id="remember"
                                        type="checkbox"
                                        className="mr-2 checked:bg-purple-700"
                                    />
                                    <label htmlFor="remember" className="text-xs font-semibold text-gray-600">{t('login.remember')}</label>
                                    <a href="/quen-mat-khau" className="text-xs font-semibold text-purple-700 ml-auto">{t('login.forgotPassword')}</a>
                                </div>
                                <div className="mb-3">
                                    <button
                                        onClick={handleLogin}
                                        className="block w-full text-white bg-blue-600 hover:bg-blue-800 px-4 py-2 rounded-md"
                                    >
                                        {t('login.login')}
                                    </button>
                                </div>
                                <a href='/' className="text-center text-blue-600 text-base underline block mb-4">
                                    {t('login.backToLogin')}
                                </a>

                            </form>
                            <form onSubmit={(e) => {
                                e.preventDefault();
                                handleLoginWithGoogle();
                            }}>
                                <div className="mb-3 text-center bg-slate-100 border rounded flex items-center">
                                    <button
                                        type="submit"
                                        className="flex hover:bg-sky-200 text-base px-4 py-2 items-center gap-4 w-full"
                                    >
                                        <img alt="svgImg"
                                             src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHg9IjBweCIgeT0iMHB4IiB2aWV3Qm94PSIwIDAgNDggNDgiIGZpbGw9IiMwMDAwMDAiIHdpZHRoPSIzMnB4IiBoZWlnaHQ9IjMycHgiPjxwYXRoIGZpbGw9IiNGRkNEMzAiIGQ9Ik0wLDI0YzAtMy4xMjUsMC42My02LjA4MiwxLjc3NS04LjgxMkwxLjc4MywxNS4xOTNMOC41LDE2LjI5NUM5LjY2NiwxMy42NzQsMTIuNjc1LDExLDE2LDExYzMuNzg4LDAsNy4xODMsMS41MjgsOS42OTQsNC4wMTZsNy4yNTUtNy4yNTVjLTQuMTA3LTMuODA2LTkuNDA3LTYuMTU2LTE2Ljk1LTYuMTU2QzcuNDI3LDIsMCwyLjQyNywwLDJDMy40NjUsMC4yMTUsNy4xODQsMCw4LjgwOSwwYy00LjM2MSw0LjM2MS05LDExLjMyLTksMjRzNC41NjksMTkuNjM5LDksMjRDMjIuNzI2LDQ4LDMzLjcwOCwwLDI0eiI+PC9wYXRoPjxwYXRoIGZpbGw9IiNGMUQ2MzUiIGQ9Ik00My42MTEsMjAuMDgzSDQyVjIwSDI0djhoMTEuMzAzYy0wLjc5MiwyLjIzNy0yLjIzMSw0LjE2Ni00LjA4Nyw1LjU3MWMwLjAwMS0wLjAwMSwwLjAwMi0wLjAwMSwwLjAwMy0wLjAwMmw2LjE5LDUuMjM4QzM2Ljk3MSwzOS4yMDUsNDQsMzQsNDQsMjRDNDQsMjIuNjU5LDQzLjg2MiwyMS4zNSw0My42MTEsMjAuMDgzeiI+PC9wYXRoPjxwYXRoIGZpbGw9IiNFRTkzMjMiIGQ9Ik0yNCw0NGM1LjE2NiwwLDkuODYtMS45NzcsMTMuNDA5LTUuMTkybC02LjE5LTUuMjM4QzI5LjIxMSwzNS4wOTEsMjYuNzE1LDM2LDI0LDM2Yy01LjIwMiwwLTkuNjE5LTMuMzE3LTExLjI4My03Ljk0NmwtNi41MjIsNS4wMjVDOS41MDUsMzkuNTU2LDE2LjIyNyw0NCwyNCw0NHoiPjwvcGF0aD48cGF0aCBmaWxsPSIjMkQ3RkYyIiBkPSJNNDQzLjYxMSwyMC4wODNINDJWMjBIMjR2OGgxMS4zMDNjLTAuNzkyLDIuMjM3LTIuMjMxLDQuMTY2LTQuMDg3LDUuNTcxYzAuMDAxLTAuMDAxLDAuMDAyLTAuMDAxLDAuMDAzLTAuMDAybDYuMTksNS4yMzhDMzYuOTcxLDM5LjIwNSw0NCwzNCw0NCwyNEM0NCwyMi42NTksNDMuODYyLDIxLjM1LDQzLjYxMSwyMC4wODN6Ij48L3BhdGg+PHBhdGggZmlsbD0iIzMyQUFGNSIgZD0iTTYuMzA2LDE0LjY5MWw2LjU3MSw0LjgxOUMxNC42NTUsMTUuMTA4LDE4Ljk2MSwxMiw0LDExYzMuMDU5LDAsNS44NDIsMS4xNTQsNy45NjEsMy4wMzlsNS42NTctNS42NTdDMzQuMDQ2LDYuMDUzLDI5LjI2OCw0LDI0LDRDMTYuMzE4LDQsOS42NTYsOC4zMzcsNi4zMDYsMTQuNjkxeiI+PC9wYXRoPgo8L3N2Zz4="/>
                                        {t('login.loginWithGoogle')}
                                    </button>
                                </div>
                            </form>

                            <div className="text-center">
                                <span className="text-xs text-gray-400 font-semibold block mb-2">{t('login.newCustomer')}</span>
                                <a href="/dang-ky" className="text-xs font-semibold text-purple-700">{t('login.signUp')}</a>
                            </div>
                        </div>
                    </div>
                    <div
                        className="hidden lg:flex flex-wrap content-center justify-center rounded-r-md bg-slate-50 w-1/2 p-4">
                        <img
                            className="bg-center bg-no-repeat bg-cover rounded-r-md"
                            src="/img/logo_footer.png"
                            alt="Background"
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;
