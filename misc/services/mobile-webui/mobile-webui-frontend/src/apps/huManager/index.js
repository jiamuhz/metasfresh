import { push } from 'connected-react-router';

import { clearLoadedData } from './actions';

import messages_en from './i18n/en.json';
import messages_de from './i18n/de.json';
import messages_zh from './i18n/zh.json';
import { huManagerReducer } from './reducers';
import { huManagerLocation, huManagerRoutes } from './routes';

export const applicationDescriptor = {
  applicationId: 'huManager',
  routes: huManagerRoutes,
  messages: {
    en: messages_en,
    de: messages_de,
    zh: messages_zh,
  },
  startApplication: () => {
    return (dispatch) => {
      dispatch(clearLoadedData());
      dispatch(push(huManagerLocation()));
    };
  },
  reduxReducer: huManagerReducer,
};
