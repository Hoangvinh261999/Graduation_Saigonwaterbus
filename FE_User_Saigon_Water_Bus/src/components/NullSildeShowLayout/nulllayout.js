import Footer from '../DefaulLayout/Footer/index.js';
import Header from '../DefaulLayout/Header/header.js';
import ChatWidget from '../DefaulLayout/Support/chatbox.js';


const NullLayout = ({children}) => {
    const images = [
        'https://saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-6.jpg',
        'https://www.thaiboatguide.com/wp-content/uploads/2023/07/EJjCifaUYAA3q2h.jpg',
        'https://cdn.anvui.vn/upload/web/2021/10/15/1634318654_w2.jpg.jpg',
    ];
    return (
        <div className='flex flex-col min-h-screen'>

            <Header/>

            <div className='py-2'>{children}</div>
            <ChatWidget/>
            <Footer/>
        </div>
    );
}

export {NullLayout};