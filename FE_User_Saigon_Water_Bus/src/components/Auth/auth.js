import React from 'react';
import ChatWidget from '../DefaulLayout/Support/chatbox';


const AuthLayout = ({children}) => {
    return (
        <div
            style={{
                backgroundImage: `url('https://staging.saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-2-2.jpg')`,
                backgroundSize: 'cover',
                backgroundPosition: 'center',
            }}
        >
            {children}
            <ChatWidget/>
        </div>
    );
}
export {AuthLayout};
