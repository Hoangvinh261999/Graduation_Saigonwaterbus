import './App.css';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import {publicRoutes} from './Route/route.js';
import {DefaultLayout} from "./components/DefaulLayout/index.js";
import {AuthLayout} from "./components/Auth/auth";
import {NullLayout} from './components/NullSildeShowLayout/nulllayout.js';
import {useAuth} from './AuthContext.js';

function App() {
    const {notification} = useAuth();

    return (
        <Router>
            <div className="">
                {notification && (
                    <div className="fixed top-0 left-0 right-0 bg-red-500 text-white text-center py-2">
                        {notification}
                    </div>
                )}
                <Routes>
                    {publicRoutes.map((route, index) => {
                        let Layout = DefaultLayout; // Default layout
                        if (route.path === '/login' || route.path === '/dang-ky' || route.path === '/quen-mat-khau'  || route.path === '/doi-mat-khau') {
                            Layout = AuthLayout;
                        } else if (route.path === '/lich-su-dat-ve' || route.path === '/dat-ve/thanh-toan-thanh-cong' ||route.path==='/dopayment' || route.path === '/dat-ve' || route.path==='/kiem-tra-ve') {
                            Layout = NullLayout;
                        }
                        const Page = route.component;
                        return (
                            <Route
                                key={index}
                                path={route.path}
                                element={
                                    <Layout>
                                        <Page/>
                                    </Layout>
                                }
                            />
                        );
                    })}
                </Routes>
            </div>
        </Router>
    );
}

export default App;
