#import <Foundation/Foundation.h>
#import <MapKit/MapKit.h>
#import "TrailPoint.h"

@interface LocationMarker : NSObject <MKAnnotation> {
    
	CLLocationCoordinate2D coordinate;
	TrailPoint* point;
}

@property (nonatomic, readonly) CLLocationCoordinate2D coordinate;
@property (nonatomic, retain) TrailPoint* point;

-(id) initWithPoint: (TrailPoint*) point;

@end
