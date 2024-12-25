import Footer from '../DefaulLayout/Footer/index.js';
import Header from '../DefaulLayout/Header/header.js';
import Carousel from '../DefaulLayout/Carosel/carosel.js';
import ChatWidget from './Support/chatbox.js';
const DefaultLayout = ({children}) => {
    const images = [
        'https://saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-6.jpg',
        'https://www.thaiboatguide.com/wp-content/uploads/2023/07/EJjCifaUYAA3q2h.jpg',
        'https://cdn.anvui.vn/upload/web/2021/10/15/1634318654_w2.jpg.jpg',
    ];
    return (
        <div className="flex flex-col min-h-screen">
            <Header/>
            <div className="flex-1">
                <div className="carousel">
                    <Carousel images={images} width="w-full" height="h-64 md:h-96 lg:h-128"/>
                </div>
                <div className="flex items-center justify-center bg-stone-200 h-40">
                    <div className=" flex ">
                        <h1 className="text-center text-4xl  font-bold ">
                            Saigon Waterbus
                        </h1>
                    </div>
                </div>
                <div className="container mx-auto py-2">
                    {children}

                </div>
                    <ChatWidget/>

            </div>
            <Footer/>
        </div>
    );
}

export {DefaultLayout};